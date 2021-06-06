#!/usr/bin/expect -f
set login "{{ sovha_login }}"
set host "{{ sovha_host }}"
set source_path "{{ sovha_source_path }}"
set dest_path "{{ sovha_dest_path }}"
set password "{{ sovha_password }}"
spawn scp $login@$host:$source_path $dest_path
expect "$login@$host's password:"
send "$password\r"
expect eof
exit