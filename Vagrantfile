# -*- mode: ruby -*-
# vi: set ft=ruby :
#This file is replica of containernet/Vagrantfile
#
# This Vagrant file create a containernet VM.
#
#
# All Vagrant configuration is done below. The "2" in Vagrant.configure
# configures the configuration version (we support older styles for
# backwards compatibility). Please don't change it unless you know what
# you're doing.
Vagrant.configure(2) do |config|

  # use ubuntu 16.04 LTS
  config.vm.box = "ubuntu/xenial64"

  config.vm.synced_folder "containernet/", "/home/ubuntu/containernet"
  config.vm.synced_folder ".", "/home/ubuntu/sos-agent"
  config.vm.synced_folder "../SOSForFloodlight", "/home/ubuntu/sos-for-floodlight"

  config.vm.provider "virtualbox" do |vb|
    vb.name = "containernet"
    vb.memory = "1024"
  end

  config.vm.provision "shell", inline: <<-SHELL
     sudo apt-get update
     sudo apt-get install -y ansible
     sudo echo "localhost ansible_connection=local" >> /etc/ansible/hosts
     # install containernet
     echo "Installing containernet (will take some time up to ~30 minutes) ..."
     cd /home/ubuntu/containernet/ansible
     sudo ansible-playbook -v install.yml

     # execute containernet tests at the end to validate installation
     echo "Running containernet unit tests to validate installation"
     cd /home/ubuntu/containernet
     sudo python setup.py develop
     sudo py.test -v mininet/test/test_containernet.py

     # place motd
     sudo cp util/motd /etc/motd
  SHELL
end
