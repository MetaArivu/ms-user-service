#!/bin/sh
# -------------------------------------------------------------------------------------------
# @author: Araf Karsh Hamid
# -------------------------------------------------------------------------------------------
mvn clean package

cp -rf target/user-service-1.0.jar Docker/

