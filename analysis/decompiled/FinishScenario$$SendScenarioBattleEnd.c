// name: FinishScenario$$SendScenarioBattleEnd
// entry: 020451fc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long FinishScenario__SendScenarioBattleEnd(undefined8 param_1,undefined4 param_2,undefined4 param_3)

{
  long lVar1;
  
  if ((bRam00000000049fea32 & 1) == 0) {
    func_0x010c65d4(0x57b5);
    bRam00000000049fea32 = 1;
  }
  lVar1 = func_0x010fdda8(_class_FinishScenario_<SendScenarioBattleEnd>c__Iterator1_TypeInfo);
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
    uRam000000000000001c = param_2;
    func_0x010eec64(0);
    uRam0000000000000020 = param_3;
    func_0x010eec64(0);
  }
  else {
    *(undefined4 *)(lVar1 + 0x1c) = param_2;
    *(undefined4 *)(lVar1 + 0x20) = param_3;
  }
  *(undefined8 *)(lVar1 + 0x28) = param_1;
  return lVar1;
}


