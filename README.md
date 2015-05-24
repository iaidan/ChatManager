<h2>ChatManager</h2>
<img src="https://api.travis-ci.org/iaidan/ChatManager.svg" alt="" />
A simple plugin to help you manage your Minecraft servers chat!

<h3>Dependencies</h3>

Bukkit API

<h3>Features</h3>

<ul>
	<li>Cross-server chat on Lilypad MC
	<ul>
		<li>BungeeCord support coming soon</li>
	</ul>
	</li>
	<li>Manage what formatting players can put in chat</li>
	<li>Control if chat is abled for the whole server, or for every individual player</li>
	<li>Range mode to allow you to limit chat to a range around any player</li>
	<li>Chat channels coming soon</li>
	<li>Restricted mode that allows only those with permission to talk</li>
</ul>

<h3>Installation</h3>

<ol>
	<li>Move ChatManager.jar into the /plugins folder</li>
	<li>Restart Spigot</li>
	<li>(Optional) Stop Spigot and edit the /plugins/nameplates/config.yml file</li>
</ol>

<h3>Commands and Permissions</h3>

<table>
	<tbody>
		<tr>
			<td> <h6>Command</h6> </td>
			<td> <h6>Function</h6> </td>
			<td> <h6>Permission</h6> </td>
			<td> <h6>Default</h6> </td>
		</tr>
		<tr>
			<td></td>
			<td> permission to speak </td>
			<td> chatmanager.chat </td>
			<td> True </td>
		</tr>
		<tr>
			<td></td>
			<td> speak even if chat is in restricted mode </td>
			<td> chatmanager.chat.override </td>
			<td> op </td>
		</tr>
		<tr>
			<td></td>
			<td> talk outside of the range restrictions </td>
			<td> chatmanager.ranged.override </td>
			<td> op </td>
		</tr>
		<tr>
			<td></td>
			<td> use colours in chat (&amp;1-9a-f)</td>
			<td> chatmanager.chat.colour </td>
			<td> true </td>
		</tr>
		<tr>
			<td> </td>
			<td> use formatting&nbsp; &nbsp;in chat (&amp;l, &amp;l, &amp;m, &amp;n, &amp;o, &amp;r)</td>
			<td> chatmanager.chat.format </td>
			<td> op </td>
		</tr>
		<tr>
			<td> </td>
			<td> use magic (&amp;k) in chat </td>
			<td> chatmanager.chat.magic </td>
			<td> op </td>
		</tr>
		<tr>
			<td> <i>/cm [show|hide]</i> </td>
			<td>toggle if they see the chat or not </td>
			<td> chatmanager.chat.showhide </td>
			<td> true </td>
		</tr>
		<tr>
			<td> <i>/cm ding</i> </td>
			<td> toggle if they hear a sound when their mentioned </td>
			<td> chatmanager.chat.ding </td>
			<td> true </td>
		</tr>
		<tr>
			<td> <i>/cm mode [enabled|disabled|restricted]</i> </td>
			<td> Allows a player to change the chats mode &ndash; please note that the two permission nodes below must be used in conjunction with this </td>
			<td> chatmanager.chat.mode </td>
			<td> op </td>
		</tr>
		<tr>
			<td> <i>/cm mode [enabled|disabled]</i> </td>
			<td> Enable or disable the whole chat </td>
			<td> chatmanager.chat.mode.on </td>
			<td> op </td>
		</tr>
		<tr>
			<td> <i>/cm mode restricted</i> </td>
			<td> Put chat into restricted mode so that only those with permission can speak </td>
			<td> chatmanager.chat.mode.restricted </td>
			<td> op </td>
		</tr>
		<tr>
			<td> <i>/cm reload</i> </td>
			<td> Reloads the plugins configurations </td>
			<td> chatmanager.reload </td>
			<td> op </td>
		</tr>
	</tbody>
</table>

<h3><a href="https://github.com/iaidan/ChatManager">Source Code</a></h3>


