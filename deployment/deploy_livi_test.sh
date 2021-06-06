cd ../
./build.sh
cd deployment
versionhash=$(git rev-parse --short HEAD)
ansible-playbook tasks/deploy.yml -i inventory/inventory_test --extra-vars "versionhash=$versionhash"