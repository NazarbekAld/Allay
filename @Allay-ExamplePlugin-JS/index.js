import {a} from './another.js'
import isEven from 'is-even' // node_modules

// This will run as soon as server loading plugin(s)
console.log("Loaded")

await pluginState.whenEnabled()
console.log("Hi")
console.log(plugin.getServer().broadcastMessage("Broadcast!"))
await pluginState.whenDisabled()
console.log("Bye")