# Installing servers & deploying

## Prerequisites (Mac)
* HomeBrew [(https://brew.sh)](https://brew.sh)
* Maven [(https://maven.apache.org/)](https://maven.apache.org/)
	* Preferred installation method `brew install maven`
* Vagrant [()https://www.vagrantup.com/](https://www.vagrantup.com/)
    * Preferred installation method `brew cask install vagrant`
* Ansible
	* Preferred installation method `sudo pip install ansible`

## Prerequisites (Windows 10)
* Windows Subsystem For linux [(https://docs.microsoft.com/en-us/windows/wsl/install-win10)](https://docs.microsoft.com/en-us/windows/wsl/install-win10)
* Ubuntu
	* Install from Store
	* Required packages:
	 * Java 8 (sudo apt-get install openjdk-8-jdk)
	 * Maven (sudo apt-get install maven)
	 * Ansible (sudo apt-get install ansible)
	 * Sshpass (sudo apt-get install sshpass)
	 * Node [(https://nodejs.org/en/download/package-manager/#debian-and-ubuntu-based-linux-distributions)](https://nodejs.org/en/download/package-manager/#debian-and-ubuntu-based-linux-distributions)
* Alternatively use a virtual machine Ubuntu

## Windows 10 notes
* For Windows Subsystem for Linux, C-drive is located in /mnt/c/
* For a virtual machine, a shared folder can be used for the files
* All files have full access --> you need to create separate password.txt in Ubuntu home folder without execution permission and change ansible.cfg to use that new file

## Vagrant
vagrant-folder contains required init-skript to start local Oracle Linux 7.4 server. Normally you don't need this, but you may use it to test ansible-changes.

* Start: run commant `vagrant up` in vagrant-folder
* You can ssh to machine using two methods
	* `vagrant ssh` (password is vagrant)
	* `ssh vagrant@192.168.60.80 -i vagrant_rsa`
* installing tomcat, haproxy etc is done using vagrant `ansible-playbook tasks/install_server.yml -i inventory/inventory_local`. Installing packages to test and prod is made same way - just change inventory-file.

## Deployment
* Optionally, set up ssh keys:
    * set up ssh keys on remote servers (ssh-keygen, ssh-copy-id)
    * set up ssh keys with Ansible 
        * see: https://docs.ansible.com/ansible/latest/user_guide/connection_details.html#ssh-key-setup
        * example from the documentation:  
            ```shell script
            ssh-agent bash
            ssh-add ~/.ssh/id_rsa
            ```

* If there are database changes, apply the new database scripts manually to the test/prod database
	* Connect to livi vpn
	* Connect to the database. See https://extranet.vayla.fi/wiki/x/YZIEAQ for instructions.
		* note that dbeaver allows saving ssh tunnel settings for a db connection, instead of having to open the tunnel separately through the command line or putty
* Go to folder `deployment`
* Copy the ansible-vault password to password.txt
* Decrypt inventory-files `ansible-vault decrypt inventory/inventory_test` or `ansible-vault decrypt inventory/inventory_prod`.
* Edit `./inventory/inventory_[prod/test]` -file
	* Change value of `ansible_ssh_user` to your livi-username
	* Change value of `ansible_become_password` to your livi-password (add the row if missing)
	    * alternatively, add the command line option `--ask-become-pass` to the `deploy_livi_*.sh` files and type the password when requested during deployment
* Encrypt inventory-files `ansible-vault encrypt inventory/inventory_test` or `ansible-vault encrypt inventory/inventory_prod`.
* Connect to livi vpn: `https://etayhteys.vayla.fi` or through a vpn client
* (Manually taking any backups from the server should be performed before deployment script)
* Run deployment script `./deploy_livi_[test/prod].sh`. This will copy new files to server.
    * The ssh password asked is for your K-username
* Test the new version:
	* Test server ui (disable vpn first): `https://testintranet.liikennevirasto.fi/tietokatalogi/#/`
