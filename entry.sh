#!/usr/bin/env bash


docker build -t binayakd86/koo-swimming-builder -f ./Dockerfile-builder .

docker build -t binayakd86/koo-swimming .

docker run -p -rm 8080:8080 binayakd86/koo-swimming