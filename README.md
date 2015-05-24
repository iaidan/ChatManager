<h2>ChatManager</h2>
<img src="https://api.travis-ci.org/iaidan/ChatManager.svg" alt="" />
<p>A simple plugin to help you manage your Minecraft servers chat!</p>

<p>&nbsp;</p>

<p><strong>Dependencies</strong></p>

<p>Bukkit API</p>

<p>&nbsp;</p>

<p><strong>Features</strong></p>

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

<p><strong>Installation</strong></p>

<ol>
	<li>Move ChatManager.jar into the /plugins folder</li>
	<li>Restart Spigot</li>
	<li>(Optional) Stop Spigot and edit the /plugins/nameplates/config.yml file</li>
</ol>

<p><strong>Commands and Permissions</strong></p>

<p>&nbsp;</p>

<table border="1" cellpadding="0" cellspacing="0" style="line-height:normal;">
	<tbody>
		<tr>
			<td style="width:109px;">
			<p><strong>Command</strong></p>
			</td>
			<td style="width:248px;">
			<p><strong>Function</strong></p>
			</td>
			<td style="width:178px;">
			<p><strong>Permission</strong></p>
			</td>
			<td style="width:63px;">
			<p><strong>Default</strong></p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">&nbsp;</td>
			<td style="width:248px;">
			<p>permission to speak</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat</p>
			</td>
			<td style="width:63px;">
			<p>True</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">&nbsp;</td>
			<td style="width:248px;">
			<p>speak even if chat is in restricted mode</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.override</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p>&nbsp;</p>
			</td>
			<td style="width:248px;">
			<p>talk outside of the range restrictions</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.ranged.override</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">&nbsp;</td>
			<td style="width:248px;">
			<p>use colours in chat (&amp;1-9a-f)</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.colour</p>
			</td>
			<td style="width:63px;">
			<p>true</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p>&nbsp;</p>
			</td>
			<td style="width:248px;">
			<p>use formatting&nbsp; &nbsp;in chat (&amp;l, &amp;l, &amp;m, &amp;n, &amp;o, &amp;r)</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.format</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p>&nbsp;</p>
			</td>
			<td style="width:248px;">
			<p>use magic (&amp;k) in chat</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.magic</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p>&nbsp;</p>
			</td>
			<td style="width:248px;">
			<p>toggle if they see the chat or not</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.showhide</p>
			</td>
			<td style="width:63px;">
			<p>true</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p>&nbsp;</p>
			</td>
			<td style="width:248px;">
			<p>toggle if they hear a sound when their mentioned</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.ding</p>
			</td>
			<td style="width:63px;">
			<p>true</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p><strong>/cm mode [enabled|disabled|restricted]</strong></p>
			</td>
			<td style="width:248px;">
			<p>Allows a player to change the chats mode &ndash; please note that the two permission nodes below must be used in conjunction with this</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.mode</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p><strong>/cm mode [enabled|disabled]</strong></p>
			</td>
			<td style="width:248px;">
			<p>Enable or disable the whole chat</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.mode.on</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p><strong>/cm mode restricted</strong></p>
			</td>
			<td style="width:248px;">
			<p>Put chat into restricted mode so that only those with permission can speak</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.chat.mode.restricted</p>
			</td>
			<td style="width:63px;">
			<p>&nbsp;</p>
			</td>
		</tr>
		<tr>
			<td style="width:109px;">
			<p><strong>/cm reload</strong></p>
			</td>
			<td style="width:248px;">
			<p>Reloads the plugins configurations</p>
			</td>
			<td style="width:178px;">
			<p>chatmanager.reload</p>
			</td>
			<td style="width:63px;">
			<p>op</p>
			</td>
		</tr>
	</tbody>
</table>

<p>&nbsp;</p>

<p><strong><a href="https://github.com/iaidan/ChatManager">Source Code</a></strong></p>

<p>&nbsp;</p>
