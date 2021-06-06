cd ../
./build.sh
cd deployment
ansible-playbook tasks/deploy.yml -i inventory/inventory_local