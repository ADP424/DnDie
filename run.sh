#!/bin/bash
cd $1
ng serve --host 0.0.0.0 --disable-host-check --ssl --ssl-cert "/ssl/me.crt" --ssl-key "/ssl/me.key"

