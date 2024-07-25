const config = {
    floor: {
        size: { x: 31, y: 30                   }
    },
    player: {
        //position: { x: 0.5, y: 0.5 },		//CENTER
        position: { x: 0.10, y: 0.18 },		//INIT
        //position: { x: 0.8, y: 0.85 },		//END
        speed: 0.2
    },
    sonars: [

 /*       {
            name: "sonar1",
            position: { x: 0.25, y: 0.00 }, //x: 0.55, y: 0.00
            senseAxis: { x: false, y: true }
        },

        {
            name: "sonar2",
            position: { x: 1.00, y: 0.95},
            senseAxis: { x: true, y: false }
        }
 */
     ],
    movingObstacles: [
/*     
        {
             name: "movingobstacle",
             position: { x: .64, y: .42 },
             directionAxis: { x: true, y: true },
             speed: 0.4,
             range: 8
         } */
    ],
   staticObstacles: [
        {
            name: "plasticBox",
            centerPosition: { x: 0.34, y: 0.38},
            size: { x: 0.03, y: 0.07}
        },
 

        {
            name: "table",
            centerPosition: { x: 0.65, y: 0.60},
            size: { x: 0.16, y: 0.14      }
		},
  /*
        {
            name: "bottle1",
            centerPosition: { x: 0.55, y: 0.8 },
            size: { x: 0.05, y: 0.05      }
		},
		
        {
            name: "bottle2",
            centerPosition: { x: 0.18, y: 0.20},
            size: { x: 0.05, y: 0.05      }
		},


 
        {
            name: "obs1",
            centerPosition: { x: 0.05, y: 0.42 }, 
            size: { x: 0.053, y: 0.041}
        },

        {
            name: "obs2",
            centerPosition: { x: 0.94, y: 0.42 }, 
            size: { x: 0.053, y: 0.041}
        },
*/		 		 
        {
            name: "wallUp",
            centerPosition: { x: 0.5, y: 1},
            size: { x: 1, y: 0}
        },
        {
            name: "wallDown",
            centerPosition: { x: 0.5, y: 0},
            size: { x: 1, y: 0}
        },
        {
            name: "wallLeft",
            centerPosition: { x: 0.00, y: 0.5},
            size: { x: 0, y: 1}
        },
        {
            name: "wallRight",
            centerPosition: { x: 1, y: 0.5},
            size: { x: 0, y: 1}
        }
    ]
}

export default config;
