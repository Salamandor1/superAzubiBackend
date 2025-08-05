# Color 

In vscode, you can either set the colour of your UI, e.g. the sidebar, through the .vscode/settiings.json file:

```javascript
"activityBar.background": "#000000",
```

Or if you are using a devcontainer.json file, you can set the color like this:

```javascript
"settings": {
	"peacock.remoteColor": "#ff69b4"
}
```

This way, everytime someone opens the project, the .vscode/settings.json is overwritten and the file gets modified to the color set in the devcontainer.json

To avoid conflict with your team, you cann either checkout the .vscode/settings.json file with 

```console
git checkout -- .vscode/settings.json 
```

Or you put .vscode/ in the .gitignore file from the start on

In our project history, we went from pink to purple to neon green, but sometimes, when you least expect it, a little bit of pink creeps into your project

![Dress color](https://pa1.narvii.com/6513/6a8669c131e3666c93fe50425f579883dce2b2bb_hq.gif)
