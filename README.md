# E-Store:  DNDie

An online E-store system built with Java 8=>11 and Angular
  
## Team

- Alex Beekman
- Mikayla Burke
- Ines Villegas Costa
- Aidan Dalgarno-Platt
- Kai Louie

## Prerequisites

- Java 8=>11 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven
- Angular

NOTE: You must have `matter-js` installed to run this project.
This will NOT be installed automatically with `npm install`!
To install `matter-js` and all prerequisites:

1. Navigate to `/estore-ui`
2. Run `npm install`
3. Run `npm install @types/matter-js`

## How to run

_Note: a live version was created at `https://mdbook.me:4200`, although this has been discontinued_

1. Clone the repository and navigate to `/estore-api`.
2. Execute `mvn compile exec:java`
3. Navigate to `/estore-ui`
4. Execute `ng serve --open`
5. Open in your browser: `http://localhost:4200/`
6. To test the admin account, login with username "Mdbook" and password "s3cure"

## Known bugs and disclaimers

No known bugs or disclaimers so far.

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory

## License

MIT License

See LICENSE for details.