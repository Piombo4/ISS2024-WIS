/* Generated by AN DISI Unibo */ 
package it.unibo.incinerator

import it.unibo.kactor.*
import alice.tuprolog.*
import unibo.basicomm23.*
import unibo.basicomm23.interfaces.*
import unibo.basicomm23.utils.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import it.unibo.kactor.sysUtil.createActor   //Sept2023

//User imports JAN2024

class Incinerator ( name: String, scope: CoroutineScope, isconfined: Boolean=false  ) : ActorBasicFsm( name, scope, confined=isconfined ){

	override fun getInitialState() : String{
		return "s0"
	}
	override fun getBody() : (ActorBasicFsm.() -> Unit){
		//val interruptedStateTransitions = mutableListOf<Transition>()
		 
					var RP: Int = 0;
					val BTIME: Long = 4000L;
		return { //this:ActionBasciFsm
				state("s0") { //this:State
					action { //it:State
						delay(500) 
						CommUtils.outgreen("$name INIZIATO")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t028",targetState="ready",cond=whenDispatch("turn_on"))
				}	 
				state("ready") { //this:State
					action { //it:State
						CommUtils.outblack("$name Acceso inceneritore")
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t129",targetState="burn_rp",cond=whenDispatch("burn_in"))
				}	 
				state("burn_rp") { //this:State
					action { //it:State
						 var RP = 1;  
						forward("burn_start", "burn_start(1)" ,"wis" ) 
						delay(BTIME)
						forward("burn_end", "burn_end(1)" ,"wis" ) 
						forward("burn_end", "burn_end(1)" ,"op_robot" ) 
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition(edgeName="t130",targetState="get_ash",cond=whenDispatch("get_ash"))
				}	 
				state("get_ash") { //this:State
					action { //it:State
						 var RP = 0;  
						//genTimer( actor, state )
					}
					//After Lenzi Aug2002
					sysaction { //it:State
					}	 	 
					 transition( edgeName="goto",targetState="ready", cond=doswitch() )
				}	 
			}
		}
} 
