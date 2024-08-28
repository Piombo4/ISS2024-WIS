%====================================================================================
% sprint0_architettura description   
%====================================================================================
%====================================================================================
context(ctx_wis, "localhost",  "TCP", "8014").
context(ctx_gui, "localhost",  "TCP", "8015").
context(ctx_wastestorage, "localhost",  "TCP", "8016").
context(ctx_ashstorage, "localhost",  "TCP", "8017").
context(ctx_incinerator, "localhost",  "TCP", "8018").
context(ctx_monitoringdevice, "localhost",  "TCP", "8019").
context(ctx_basicrobot, "localhost",  "TCP", "8020").
context(ctx_oprobot, "localhost",  "TCP", "8020").
 qactor( wis, ctx_wis, "it.unibo.wis.Wis").
 static(wis).
  qactor( incinerator, ctx_wis, "it.unibo.incinerator.Incinerator").
 static(incinerator).
  qactor( led, ctx_monitoringdevice, "it.unibo.led.Led").
 static(led).
  qactor( sonar, ctx_monitoringdevice, "it.unibo.sonar.Sonar").
 static(sonar).
  qactor( basicrobot, ctx_basicrobot, "it.unibo.basicrobot.Basicrobot").
 static(basicrobot).
