// name: BattlePlay.BattlePlayManager$$GetBattleUser
// entry: 01975aa4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

undefined8 BattlePlay_BattlePlayManager__GetBattleUser(long param_1,undefined8 param_2)

{
  ulong uVar1;
  undefined8 uVar2;
  long lVar3;
  
  if ((bRam00000000049fae03 & 1) == 0) {
    func_0x010c65d4(0x206a);
    bRam00000000049fae03 = 1;
  }
  if (*(long *)(param_1 + 0x18) != 0) {
    lVar3 = *(long *)(*(long *)(param_1 + 0x18) + 0x20);
    if (lVar3 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = System_Collections_Generic_Dictionary<ulong,_UserUnit>__ContainsKey
                      (lVar3,param_2,
                       _mm_Method_System_Collections_Generic_Dictionary<ulong,_BattleUser>_ContainsKey__
                      );
    if ((uVar1 & 1) != 0) {
      lVar3 = *(long *)(param_1 + 0x18);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      lVar3 = *(long *)(lVar3 + 0x20);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = System_Collections_Generic_Dictionary<ulong,_object>__get_Item
                        (lVar3,param_2,
                         _mm_Method_System_Collections_Generic_Dictionary<ulong,_BattleUser>_get_Item__
                        );
      return uVar2;
    }
  }
  return 0;
}


