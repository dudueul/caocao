// name: BattlePlay.BattlePlayManager$$AddMissedControls
// entry: 01977194

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__AddMissedControls(long param_1,long param_2)

{
  int iVar1;
  undefined4 uVar2;
  int iVar3;
  undefined8 uVar4;
  ulong uVar5;
  long lVar6;
  long lVar7;
  long *plVar8;
  uint uVar9;
  
  if ((bRam00000000049fae0a & 1) == 0) {
    func_0x010c65d4(0x205c);
    bRam00000000049fae0a = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  iVar1 = *(int *)(param_2 + 0x18);
  uVar9 = 0;
  while( true ) {
    lVar6 = *(long *)(param_1 + 0x18);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar6);
    if (iVar1 <= (int)uVar9) break;
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    if (*(uint *)(param_2 + 0x18) <= uVar9) {
      uVar4 = func_0x010ef850();
      func_0x010eebec(uVar4,0,0);
    }
    plVar8 = (long *)(param_2 + (long)(int)uVar9 * 8 + 0x20);
    lVar6 = *plVar8;
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (iVar3 <= *(int *)(lVar6 + 0x1c)) {
      lVar6 = *(long *)(param_1 + 0x20);
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      if (*(uint *)(param_2 + 0x18) <= uVar9) {
        uVar4 = func_0x010ef850();
        func_0x010eebec(uVar4,0,0);
      }
      lVar7 = *plVar8;
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      uVar2 = *(undefined4 *)(lVar7 + 0x1c);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      uVar5 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                        (lVar6,uVar2,
                         _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                        );
      if ((uVar5 & 1) == 0) {
        lVar6 = *(long *)(param_1 + 0x20);
        if (param_2 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(param_2 + 0x18) <= uVar9) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        lVar7 = *plVar8;
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar2 = *(undefined4 *)(lVar7 + 0x1c);
        if (*(uint *)(param_2 + 0x18) <= uVar9) {
          uVar4 = func_0x010ef850();
          func_0x010eebec(uVar4,0,0);
        }
        lVar7 = *plVar8;
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Add
                  (lVar6,uVar2,lVar7,
                   _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Add__);
      }
    }
    uVar9 = uVar9 + 1;
  }
  while( true ) {
    if ((bRam00000000049fae0f & 1) == 0) {
      func_0x010c65d4(0x2060);
      bRam00000000049fae0f = 1;
    }
    lVar6 = *(long *)(param_1 + 0x20);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar5 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                      (lVar6,iVar3,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                      );
    if ((uVar5 & 1) == 0) break;
    lVar6 = *(long *)(param_1 + 0x20);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                      (lVar6,iVar3,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_get_Item__
                      );
    lVar7 = *(long *)(param_1 + 0x20);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    uVar2 = *(undefined4 *)(lVar6 + 0x1c);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    System_Collections_Generic_Dictionary<int,_EndPointListener>__Remove
              (lVar7,uVar2,
               _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Remove__);
    iVar1 = *(int *)(lVar6 + 0x1c);
    lVar7 = *(long *)(param_1 + 0x18);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar7);
    if (iVar1 == iVar3) {
      lVar7 = *(long *)(param_1 + 0x18);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      BattlePlay_BattlePlayData__AddControl(lVar7,lVar6);
    }
    lVar6 = *(long *)(param_1 + 0x18);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar6);
  }
  return;
}


