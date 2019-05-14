# JavaBackdoor
A simple backdoor written in Java.

## Usage
Run `java backdoor` on victim's machine. Connect via Telnet using `telnet <Victim IP> 4444` so you can run shell command with the victim privileges. 

You can find the IP address of the victim with Nmap.

## Commands List
- `CMDLIST` - show all commands and what theirs do.
- `QUIT` - exit from shell.
- `GETHOME`- return the user home path.
- `GETDIR` - return the current direcotry path.
- `GETNAME`- return the user name.
- `OSARCH` - return the Operating System architecture.
- `OSNAME` - return the Operating System name.
- `OSVERS` - return the Operating System version.

