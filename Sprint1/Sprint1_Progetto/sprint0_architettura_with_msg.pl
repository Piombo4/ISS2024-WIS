%====================================================================================
% sprint0_architettura_with_msg description   
%====================================================================================
dispatch( start_robot, start_robot(X) ).
dispatch( update_gui, update_gui(X) ).
event( waste_in, waste_in(X) ).
dispatch( waste_qty, waste_qty(X) ).
dispatch( get_waste, get_waste(X) ).
event( turn_on, turn_on(X) ).
dispatch( burn_in, burn_in(X) ).
dispatch( burning, burning(X) ).
dispatch( burn_out, burn_out(X) ).
dispatch( ash_out, ash_out(X) ).
event( get_ash, get_ash(X) ).
dispatch( ash_qty, ash_qty(X) ).
dispatch( ash_full, ash_full(X) ).
dispatch( led_on, led_on(X) ).
dispatch( led_off, led_off(X) ).
dispatch( blink, blink(X) ).
request( doplan, doplan(X) ).
dispatch( robot_status, robot_status(X) ).
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_gui, "localhost",  "TCP", "8015").
context(ctx_monitoringdevice, "localhost",  "TCP", "8019").
context(ctx_basic_robot, "localhost",  "TCP", "8020").
 qactor( basic_robot, ctx_basic_robot, "external").
  qactor( oprobot, ctx_wis, "it.unibo.oprobot.Oprobot").
 static(oprobot).
  qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( ssgui, ctx_gui, "it.unibo.ssgui.Ssgui").
 static(ssgui).
  qactor( wastestorage, ctx_wis, "it.unibo.wastestorage.Wastestorage").
 static(wastestorage).
  qactor( ashstorage, ctx_wis, "it.unibo.ashstorage.Ashstorage").
 static(ashstorage).
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( led, ctx_monitoringdevice, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctx_monitoringdevice, "it.unibo.sonar.Sonar").
 static(sonar).
