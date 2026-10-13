// name: BattlePlay.BattlePlayManager$$FinishLoadingAndLeaveWatch
// entry: 0197b0fc

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

long BattlePlay_BattlePlayManager__FinishLoadingAndLeaveWatch(undefined8 param_1)

{
  long lVar1;
  
  if ((bRam00000000049fae19 & 1) == 0) {
    func_0x010c65d4(0x2069);
    bRam00000000049fae19 = 1;
  }
  lVar1 = func_0x010fdda8(
                         _class_BattlePlay_BattlePlayManager_<FinishLoadingAndLeaveWatch>c__Iterator2_TypeInfo
                         );
  System_Object___ctor(lVar1,0);
  if (lVar1 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar1 + 0x10) = param_1;
  return lVar1;
}


