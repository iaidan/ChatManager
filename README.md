<h2>ChatManager</h2>
<img src="https://api.travis-ci.org/iaidan/ChatManager.svg" alt="" />


 

**Dependencies**

Bukkit API

 

**Features**

- Cross-server chat on Lilypad MC
    - BungeeCord support coming soon

- Manage what formatting players can put in chat
- Control if chat is abled for the whole server, or for every individual player
- Range mode to allow you to limit chat to a range around any player
- Chat channels coming soon
- Restricted mode that allows only those with permission to talk

**Installation**

1. Move ChatManager.jar into the /plugins folder
2. Restart Spigot
3. (Optional) Stop Spigot and edit the /plugins/nameplates/config.yml file

**Commands and Permissions**

 

 

**Command**

 

**Function**

 

**Permission**

 

**Default**

 

permission to speak

 

chatmanager.chat

 

True

 

speak even if chat is in restricted mode

 

chatmanager.chat.override

 

op

 

** **

 

talk outside of the range restrictions

 

chatmanager.ranged.override

 

op

 

use colours in chat (&1-9a-f)

 

chatmanager.chat.colour

 

true

 

** **

 

use formatting   in chat (&l, &l, &m, &n, &o, &r)

 

chatmanager.chat.format

 

op

 

** **

 

use magic (&k) in chat

 

chatmanager.chat.magic

 

op

 

** **

 

toggle if they see the chat or not

 

chatmanager.chat.showhide

 

true

 

** **

 

toggle if they hear a sound when their mentioned

 

chatmanager.chat.ding

 

true

 

**/cm mode [enabled|disabled|restricted]**

 

Allows a player to change the chats mode – please note that the two permission nodes below must be used in conjunction with this

 

chatmanager.chat.mode

 

op

 

**/cm mode [enabled|disabled]**

 

Enable or disable the whole chat

 

chatmanager.chat.mode.on

 

op

 

**/cm mode restricted**

 

Put chat into restricted mode so that only those with permission can speak

 

chatmanager.chat.mode.restricted

 

 

 

**/cm reload**

 

Reloads the plugins configurations

 

chatmanager.reload

 

op

 

**[Source Code](https://github.com/iaidan/ChatManager)**

 
