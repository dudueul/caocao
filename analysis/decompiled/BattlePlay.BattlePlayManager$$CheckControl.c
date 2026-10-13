// name: BattlePlay.BattlePlayManager$$CheckControl
// entry: 01977038

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__CheckControl(long param_1,undefined4 param_2)

{
  undefined4 uVar1;
  int iVar2;
  int iVar3;
  ulong uVar4;
  long lVar5;
  long lVar6;
  
  while( true ) {
    if ((bRam00000000049fae0f & 1) == 0) {
      func_0x010c65d4(0x2060);
      bRam00000000049fae0f = 1;
    }
    lVar5 = *(long *)(param_1 + 0x20);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                      (lVar5,param_2,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                      );
    if ((uVar4 & 1) == 0) break;
    lVar5 = *(long *)(param_1 + 0x20);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    lVar5 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                      (lVar5,param_2,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_get_Item__
                      );
    lVar6 = *(long *)(param_1 + 0x20);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = *(undefined4 *)(lVar5 + 0x1c);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<int,_EndPointListener>__Remove
              (lVar6,uVar1,
               _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Remove__);
    iVar2 = *(int *)(lVar5 + 0x1c);
    lVar6 = *(long *)(param_1 + 0x18);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar6);
    if (iVar2 == iVar3) {
      lVar6 = *(long *)(param_1 + 0x18);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      BattlePlay_BattlePlayData__AddControl(lVar6,lVar5);
    }
    lVar5 = *(long *)(param_1 + 0x18);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    param_2 = BattlePlay_BattlePlayData__get_controlIdx(lVar5);
  }
  return;
}


