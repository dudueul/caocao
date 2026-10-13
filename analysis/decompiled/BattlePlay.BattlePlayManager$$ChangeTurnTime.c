// name: BattlePlay.BattlePlayManager$$ChangeTurnTime
// entry: 019773b8

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void BattlePlay_BattlePlayManager__ChangeTurnTime(long param_1,long param_2)

{
  int iVar1;
  undefined4 uVar2;
  int iVar3;
  uint uVar4;
  int iVar5;
  int iVar6;
  ulong uVar7;
  undefined8 uVar8;
  ulong uVar9;
  long *plVar10;
  long lVar11;
  long lVar12;
  long lVar13;
  undefined1 auVar14 [16];
  long lStack_58;
  
  if ((bRam00000000049fae0b & 1) == 0) {
    func_0x010c65d4(0x205d);
    bRam00000000049fae0b = 1;
  }
  lVar12 = *(long *)(param_1 + 0x18);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = System_Collections_Generic_List<int>__ToArray
                    (param_2,_mm_Method_System_Collections_Generic_List<int>_ToArray__);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  *(undefined8 *)(lVar12 + 0x60) = uVar8;
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                     (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar12 == 0) {
    func_0x010eec64(0);
  }
  uVar8 = ControlFlow__get_unitTurnOwner(lVar12,0);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_UnityEngine_Object_TypeInfo);
  }
  uVar9 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
  if ((uVar9 & 1) != 0) {
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    plVar10 = (long *)ControlFlow__get_unitTurnOwner(lVar12,0);
    if (plVar10 == (long *)0x0) {
      func_0x010eec64(0);
    }
    iVar6 = (**(code **)(*plVar10 + 0x260))(plVar10,*(undefined8 *)(*plVar10 + 0x268));
    lVar12 = *(long *)(param_1 + 0x18);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    plVar10 = (long *)(lVar12 + 0x40);
    if (iVar6 != 0) {
      plVar10 = (long *)(lVar12 + 0x48);
    }
    lVar12 = *plVar10;
    if (lVar12 != 0) {
      if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
      if (lVar11 == 0) {
        func_0x010eec64(0);
      }
      iVar6 = ControlFlow__get_currentUnitTurn(lVar11,0);
      *(int *)(lVar12 + 0xac) = iVar6 + -2;
      uVar8 = *(undefined8 *)(lVar12 + 0x10);
      if ((bRam00000000049fae0d & 1) == 0) {
        func_0x010c65d4(0x206e);
        bRam00000000049fae0d = 1;
      }
      lStack_58 = 0;
      lVar12 = *(long *)(param_1 + 0x18);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      lVar12 = *(long *)(lVar12 + 0x20);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      uVar9 = System_Collections_Generic_Dictionary<ulong,_UserUnitGroup>__TryGetValue
                        (lVar12,uVar8,&lStack_58,
                         _mm_Method_System_Collections_Generic_Dictionary<ulong,_BattleUser>_TryGetValue__
                        );
      lVar12 = lStack_58;
      if ((uVar9 & 1) != 0) {
        if (lStack_58 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = lStack_58;
        iVar6 = *(int *)(lVar12 + 0xac);
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        uVar2 = ControlFlow__get_currentUnitTurn(lVar12,0);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        *(undefined4 *)(lVar11 + 0xac) = uVar2;
        lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        iVar3 = ControlFlow__get_currentUnitTurn(lVar12,0);
        iVar3 = iVar3 - iVar6;
        if (1 < iVar3) {
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          iVar6 = ControlFlow__get_currentTurnOwner(lVar12,0);
          lVar12 = lStack_58;
          lVar11 = *(long *)(param_1 + 0x18);
          if (lStack_58 == 0) {
            func_0x010eec64(0);
          }
          iVar1 = *(int *)(lVar12 + 0x38);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          lVar12 = *(long *)(lVar11 + 0x40);
          if (lVar12 == 0) {
            func_0x010eec64(0);
          }
          if (iVar6 == (uint)(*(int *)(lVar12 + 0x38) != iVar1) * 2) {
            lVar11 = *(long *)(param_1 + 0x18);
            lVar12 = lVar11;
            if (lVar11 == 0) {
              func_0x010eec64(0);
              lVar12 = *(long *)(param_1 + 0x18);
              if (lVar12 == 0) {
                auVar14 = func_0x010eec64(0);
                uVar9 = auVar14._8_8_;
                lVar12 = auVar14._0_8_;
                while( true ) {
                  if ((bRam00000000049fae0f & 1) == 0) {
                    func_0x010c65d4(0x2060);
                    bRam00000000049fae0f = 1;
                  }
                  lVar11 = *(long *)(lVar12 + 0x20);
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                  }
                  uVar7 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                                    (lVar11,uVar9 & 0xffffffff,
                                     _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                                    );
                  if ((uVar7 & 1) == 0) break;
                  lVar11 = *(long *)(lVar12 + 0x20);
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                  }
                  lVar11 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                                     (lVar11,uVar9 & 0xffffffff,
                                      _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_get_Item__
                                     );
                  lVar13 = *(long *)(lVar12 + 0x20);
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                  }
                  uVar2 = *(undefined4 *)(lVar11 + 0x1c);
                  if (lVar13 == 0) {
                    func_0x010eec64(0);
                  }
                  System_Collections_Generic_Dictionary<int,_EndPointListener>__Remove
                            (lVar13,uVar2,
                             _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Remove__
                            );
                  iVar6 = *(int *)(lVar11 + 0x1c);
                  lVar13 = *(long *)(lVar12 + 0x18);
                  if (lVar13 == 0) {
                    func_0x010eec64(0);
                  }
                  iVar3 = BattlePlay_BattlePlayData__get_controlIdx(lVar13);
                  if (iVar6 == iVar3) {
                    lVar13 = *(long *)(lVar12 + 0x18);
                    if (lVar13 == 0) {
                      func_0x010eec64(0);
                    }
                    BattlePlay_BattlePlayData__AddControl(lVar13,lVar11);
                  }
                  lVar11 = *(long *)(lVar12 + 0x18);
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                  }
                  uVar9 = BattlePlay_BattlePlayData__get_controlIdx(lVar11);
                }
                return;
              }
            }
            lVar11 = *(long *)(lVar11 + 0x60);
            lVar12 = *(long *)(lVar12 + 0x60);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            iVar6 = UnityEngine_Mathf__Min(0,iVar3,*(undefined4 *)(lVar12 + 0x18),0);
            uVar4 = UnityEngine_Mathf__Max(0,0,iVar6 + -1,0);
            if (lVar11 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar11 + 0x18) <= uVar4) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            iVar6 = *(int *)(lVar11 + (long)(int)uVar4 * 4 + 0x20);
            lVar12 = *(long *)(param_1 + 0x18);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            lVar12 = *(long *)(lVar12 + 0x60);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            if (*(int *)(lVar12 + 0x18) == 0) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            iVar3 = *(int *)(lVar12 + 0x20);
            lVar12 = *(long *)(param_1 + 0x18);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            lVar12 = *(long *)(lVar12 + 0x60);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            if (*(int *)(lVar12 + 0x18) == 0) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            iVar1 = *(int *)(lVar12 + 0x20);
            if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                               (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            iVar5 = ControlFlow__get_remainedSeconds(lVar12,0);
            if (((*(byte *)(_class_UnityEngine_Mathf_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_UnityEngine_Mathf_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uVar2 = UnityEngine_Mathf__Min(0,iVar3 - iVar6,iVar1 - iVar5,0);
            if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                               (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar12 == 0) {
              func_0x010eec64(0);
            }
            ControlFlow__AddUnitTurnTime(lVar12,uVar2,5,0);
          }
        }
      }
      return;
    }
  }
  return;
}


