// name: BattlePlay.BattlePlayManager$$ResetUnitTurnTime
// entry: 01976c50

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__ResetUnitTurnTime(long param_1,undefined8 param_2,ulong param_3)

{
  undefined4 uVar1;
  int iVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  int iVar6;
  ulong uVar7;
  undefined8 uVar8;
  ulong uVar9;
  long lVar10;
  long lVar11;
  long lVar12;
  undefined1 auVar13 [16];
  long lStack_58;
  
  if ((bRam00000000049fae0d & 1) == 0) {
    func_0x010c65d4(0x206e);
    bRam00000000049fae0d = 1;
  }
  lStack_58 = 0;
  lVar10 = *(long *)(param_1 + 0x18);
  if (lVar10 == 0) {
    func_0x010eec64(0);
  }
  lVar10 = *(long *)(lVar10 + 0x20);
  if (lVar10 == 0) {
    func_0x010eec64(0);
  }
  uVar7 = System_Collections_Generic_Dictionary<ulong,_UserUnitGroup>__TryGetValue
                    (lVar10,param_2,&lStack_58,
                     _mm_Method_System_Collections_Generic_Dictionary<ulong,_BattleUser>_TryGetValue__
                    );
  lVar10 = lStack_58;
  if ((uVar7 & 1) != 0) {
    if (lStack_58 == 0) {
      func_0x010eec64(0);
    }
    lVar12 = lStack_58;
    iVar3 = *(int *)(lVar10 + 0xac);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    uVar1 = ControlFlow__get_currentUnitTurn(lVar10,0);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    *(undefined4 *)(lVar12 + 0xac) = uVar1;
    lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar10 == 0) {
      func_0x010eec64(0);
    }
    iVar2 = ControlFlow__get_currentUnitTurn(lVar10,0);
    iVar2 = iVar2 - iVar3;
    if ((1 < iVar2) || ((param_3 & 1) != 0)) {
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      iVar3 = ControlFlow__get_currentTurnOwner(lVar10,0);
      lVar10 = lStack_58;
      lVar12 = *(long *)(param_1 + 0x18);
      if (lStack_58 == 0) {
        func_0x010eec64(0);
      }
      iVar6 = *(int *)(lVar10 + 0x38);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      lVar10 = *(long *)(lVar12 + 0x40);
      if (lVar10 == 0) {
        func_0x010eec64(0);
      }
      if (iVar3 == (uint)(*(int *)(lVar10 + 0x38) != iVar6) * 2) {
        lVar12 = *(long *)(param_1 + 0x18);
        lVar10 = lVar12;
        if (lVar12 == 0) {
          func_0x010eec64(0);
          lVar10 = *(long *)(param_1 + 0x18);
          if (lVar10 == 0) {
            auVar13 = func_0x010eec64(0);
            uVar7 = auVar13._8_8_;
            lVar10 = auVar13._0_8_;
            while( true ) {
              if ((bRam00000000049fae0f & 1) == 0) {
                func_0x010c65d4(0x2060);
                bRam00000000049fae0f = 1;
              }
              lVar12 = *(long *)(lVar10 + 0x20);
              if (lVar12 == 0) {
                func_0x010eec64(0);
              }
              uVar9 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                                (lVar12,uVar7 & 0xffffffff,
                                 _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                                );
              if ((uVar9 & 1) == 0) break;
              lVar12 = *(long *)(lVar10 + 0x20);
              if (lVar12 == 0) {
                func_0x010eec64(0);
              }
              lVar12 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                                 (lVar12,uVar7 & 0xffffffff,
                                  _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_get_Item__
                                 );
              lVar11 = *(long *)(lVar10 + 0x20);
              if (lVar12 == 0) {
                func_0x010eec64(0);
              }
              uVar1 = *(undefined4 *)(lVar12 + 0x1c);
              if (lVar11 == 0) {
                func_0x010eec64(0);
              }
              System_Collections_Generic_Dictionary<int,_EndPointListener>__Remove
                        (lVar11,uVar1,
                         _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Remove__
                        );
              iVar3 = *(int *)(lVar12 + 0x1c);
              lVar11 = *(long *)(lVar10 + 0x18);
              if (lVar11 == 0) {
                func_0x010eec64(0);
              }
              iVar2 = BattlePlay_BattlePlayData__get_controlIdx(lVar11);
              if (iVar3 == iVar2) {
                lVar11 = *(long *)(lVar10 + 0x18);
                if (lVar11 == 0) {
                  func_0x010eec64(0);
                }
                BattlePlay_BattlePlayData__AddControl(lVar11,lVar12);
              }
              lVar12 = *(long *)(lVar10 + 0x18);
              if (lVar12 == 0) {
                func_0x010eec64(0);
              }
              uVar7 = BattlePlay_BattlePlayData__get_controlIdx(lVar12);
            }
            return;
          }
        }
        lVar12 = *(long *)(lVar12 + 0x60);
        lVar10 = *(long *)(lVar10 + 0x60);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        iVar3 = UnityEngine_Mathf__Min(0,iVar2,*(undefined4 *)(lVar10 + 0x18),0);
        uVar4 = UnityEngine_Mathf__Max(0,0,iVar3 + -1,0);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar12 + 0x18) <= uVar4) {
          uVar8 = func_0x010ef850();
          func_0x010eebec(uVar8,0,0);
        }
        iVar3 = *(int *)(lVar12 + (long)(int)uVar4 * 4 + 0x20);
        lVar10 = *(long *)(param_1 + 0x18);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        lVar10 = *(long *)(lVar10 + 0x60);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        if (*(int *)(lVar10 + 0x18) == 0) {
          uVar8 = func_0x010ef850();
          func_0x010eebec(uVar8,0,0);
        }
        iVar2 = *(int *)(lVar10 + 0x20);
        lVar10 = *(long *)(param_1 + 0x18);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        lVar10 = *(long *)(lVar10 + 0x60);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        if (*(int *)(lVar10 + 0x18) == 0) {
          uVar8 = func_0x010ef850();
          func_0x010eebec(uVar8,0,0);
        }
        iVar6 = *(int *)(lVar10 + 0x20);
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        iVar5 = ControlFlow__get_remainedSeconds(lVar10,0);
        iVar6 = iVar6 - iVar5;
        if ((param_3 & 1) == 0) {
          if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          iVar6 = UnityEngine_Mathf__Min(0,iVar2 - iVar3,iVar6,0);
        }
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar10 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        ControlFlow__AddUnitTurnTime(lVar10,iVar6,5,0);
      }
    }
  }
  return;
}


