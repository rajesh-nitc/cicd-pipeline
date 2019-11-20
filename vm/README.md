## vm

create ssh keys for the rajesh_nitc_gcp user:
ssh-keygen -t rsa -C "The access key for"
cat ~/.ssh/id_rsa.pub > ~/.ssh/authorized_keys
cat id_rsa
COPY id_rsa i.e. private key to the jenkins credentials management

install ssh agent plugin

give permission to rajesh_nitc_gcp to scp the jar file in the target folder:
sudo chown -R rajesh_nitc_gcp /opt/app

sudo: no tty present and no askpass program specified' error?:
sudo visudo
rajesh_nitc_gcp ALL=(ALL) NOPASSWD: ALL