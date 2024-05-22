#!/bin/bash
./mvnw clean
./mvnw package
docker build -f Dockerfile -t atv-sem2fase3cap8 .