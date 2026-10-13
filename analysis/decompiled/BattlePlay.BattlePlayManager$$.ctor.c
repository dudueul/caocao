// name: BattlePlay.BattlePlayManager$$.ctor
// entry: 01975918

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined1  [16] BattlePlay_BattlePlayManager___ctor(ulong param_1)

{
  undefined8 uVar1;
  undefined1 auVar2 [16];
  
  if ((bRam00000000049fae00 & 1) == 0) {
    func_0x010c65d4(0x2076);
    bRam00000000049fae00 = 1;
  }
  uVar1 = func_0x010fdda8(_class_System_Collections_Generic_Dictionary<int,_BattleControl>_TypeInfo)
  ;
  System_Collections_Generic_Dictionary<int,_WorldBossShopHistory>___ctor
            (uVar1,_mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>__ctor__);
  *(undefined8 *)(param_1 + 0x20) = uVar1;
  if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if (param_1 == 0) {
    func_0x010eec64(0,_mm_Method_Singleton<BattlePlayManager>__ctor__);
  }
  if ((bRam0000000004a05620 & 1) == 0) {
    func_0x010c65d4(0x2e83,0);
    bRam0000000004a05620 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  auVar2._8_8_ = 0;
  auVar2._0_8_ = param_1;
  return auVar2;
}


