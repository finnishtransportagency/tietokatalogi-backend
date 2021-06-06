#!/bin/bash
export REACT_APP_BASE_URL=tietokatalogi
export REACT_APP_BASE_REST_URL=tietokatalogi/rest
export REACT_APP_VERSION_HASH=$(git rev-parse --short HEAD)

echo "Building front end..."
pushd src/main/app

# If environment has changed since last npm install sass might fail
# Can be removed if causes other problems and the env stays stable
npm rebuild node-sass

npm install
npm run build
rm -rf ../webapp/build/*
cp -r build/* ../webapp/
rm -rf build
popd
echo "... done!"

echo "Building... "
	mvn clean install -Dmaven.test.skip=true
echo "... done!"
