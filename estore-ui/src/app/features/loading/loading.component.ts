import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import * as Matter from 'matter-js';

@Component({
  selector: 'app-loading',
  templateUrl: './loading.component.html',
  styleUrls: ['./loading.component.css']
})

export class LoadingComponent implements OnInit {
  //Define initial variables
  //TODO: automatically fetch all asset paths and put them into array
  images = [
    "/assets/images/dice.png",
    "/assets/images/logo.png",
    "/assets/images/wilds.jpg",
    "/assets/dice/Aqua.png",
    "/assets/dice/Aquamarine.png",
    "/assets/dice/Classic.png",
    "/assets/dice/Dark.png",
    "/assets/dice/Eyesore.png",
    "/assets/dice/Green-shimmer.png",
    "/assets/dice/Grey.png",
    "/assets/dice/Pink-shimmer.png",
    "/assets/dice/Purple.png",
    "/assets/dice/Satan.png",
    "/assets/dice/Void.png",
  ];
  assetsLoaded = 0;
  allLoaded = false

  constructor(public router: Router) {}

  /**
   * Load the assets and prepare the loading animation
   */
  ngOnInit(): void {
    // preload all assets
    this.loadAssets();

    // sSet the number of cycles to roll the dice for
    var numIterations:number = 3;

    // test for infinite loading
    if (window.location.href.indexOf("#infinite") != -1){
      numIterations = -1;
    }

    // delay here, otherwise the container element won't have been created yet
    setTimeout(() => {
      rollDice(() => {
        if (window.location.href.indexOf("/loading") != -1){
          //Test to see if we are still on the loading screen (in case of users navigating backwards)
          this.router.navigate(['/home']);
        }
      }, 0, this);
    }, 100);
  }
  
  //Preload assets
  loadAssets(){
    //Cycle through list of images
    for(let i = 0; i < this.images.length; i++){
      //Create image
      let img = new Image();
      //Call loaded() once image has finished loading
      img.onload = () => {
        this.loaded(i);
      }
      //Set the image source
      img.src = this.images[i];
    }
  }
  
  loaded(i:number){
    //Increase the assetsLoaded counter
    this.assetsLoaded++;
    console.log("Loaded " + this.images[i])
    //Test to see if all assets have been loaded
    if(this.images.length == this.assetsLoaded){
      //TODO: tie this into the rollDice function
      this.allLoaded = true
      console.log("All assets loaded");
    }
  }


}

/**
 * Perform the rolling animation
 * @param loadPage function to be called when the dice roll has happened numIterations times
 * @param counter count how many times dice has rolled
 * @param component the component to do the animation on
 */
function rollDice(loadPage:Function, counter:number, component:LoadingComponent) {
  //Define initial variables
  var Engine = Matter.Engine,
      Render = Matter.Render,
      Runner = Matter.Runner,
      Bodies = Matter.Bodies,
      Composite = Matter.Composite;
  // Get the container element
  var loadanimation = document.getElementById('loadanimation');
  // create an engine
  var engine = Engine.create();
  // create a renderer
  var render = Render.create({
    // We need this test in order for angular to compile properly because angular is smol brain
      element: loadanimation ? loadanimation : document.body,
      engine: engine,
      options: {
          wireframes: false,
          background: 'rgb(4, 135, 196);',
          height: 800,
          width: 1200,
      }
  });

  // Create dice
  var dice = Bodies.polygon(0, 150, 6, 80, {
      render: {
          sprite: {
            // Resize the logo to fit the sprite
              texture: "/assets/images/logo.png",
              xScale: 0.19,
              yScale: 0.19
          }
      }
  });

  // Create the floor
  var floor = Bodies.rectangle(600, 300, 420, 10, {
      isStatic: true
  });
  engine.gravity.y = 2;

  // Add the dice and floor to the world
  Composite.add(engine.world, [dice, floor]);

  // Set the initial velocity of the dice
  Matter.Body.setVelocity(dice, {
      x: 3,
      y: -2
  });

  // Add some initial force to make it spin
  Matter.Body.applyForce(dice, Matter.Vector.create(1, 0), Matter.Vector.create(0.5, 0));

  // Run the renderer
  Render.run(render);

  // Create runner
  var runner = Runner.create();
  // Run the engine
  Runner.run(runner, engine);
  //Check y position of the dice every 50 milliseconds
  var checkInt = setInterval(function(){
    //Check to make sure the user is still on the loading page
    if (window.location.href.indexOf("/loading") == -1){
      console.warn("Loading animation hasn't finished, but user is no longer on the loading page!");
      //If not, clear the animation and stop the check
      clearInterval(checkInt);
      Matter.Engine.clear(engine);
      Matter.Render.stop(render);
      Matter.Runner.stop(runner);
      render.canvas.remove();
      return;
    }
    //Check if the dice has fallen below the screen
    if (dice.position.y >= 800) {
      //Clear the checking interval
      clearInterval(checkInt);
      //Reset the render
      Matter.Engine.clear(engine);
      Matter.Render.stop(render);
      Matter.Runner.stop(runner);
      render.canvas.remove();
      //Increase the counter
      counter++;
      //Check to see if we should repeat or call the loadPage function
      if (component.allLoaded){
        //Call loadPage and exit
        loadPage();
      } else { //Test to see if we are still on the loading screen (in case of users navigating backwards)
        //Re-roll the dice
        rollDice(loadPage, counter, component);
      }
    }
  }, 50);
}
