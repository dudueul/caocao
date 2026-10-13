// name: Multiplay.MultiplayManager$$OnTurnInfo
// entry: 013a9bd8

/* WARNING: Removing unreachable block (ram,0x016e6350) */
/* WARNING: Removing unreachable block (ram,0x016e64b0) */
/* WARNING: Removing unreachable block (ram,0x016e64c4) */
/* WARNING: Removing unreachable block (ram,0x016e64cc) */
/* WARNING: Removing unreachable block (ram,0x016e64d0) */
/* WARNING: Removing unreachable block (ram,0x016e64ec) */
/* WARNING: Removing unreachable block (ram,0x016e64f4) */
/* WARNING: Removing unreachable block (ram,0x016e6510) */
/* WARNING: Removing unreachable block (ram,0x016e6518) */
/* WARNING: Removing unreachable block (ram,0x016e651c) */
/* WARNING: Removing unreachable block (ram,0x016e6538) */
/* WARNING: Removing unreachable block (ram,0x016e654c) */
/* WARNING: Removing unreachable block (ram,0x016e6554) */
/* WARNING: Removing unreachable block (ram,0x016e6558) */
/* WARNING: Removing unreachable block (ram,0x016e65cc) */
/* WARNING: Removing unreachable block (ram,0x016e65d4) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */
/* WARNING: Restarted to delay deadcode elimination for space: ram */

void Multiplay_MultiplayManager__OnTurnInfo(long param_1,long param_2)

{
  double dVar1;
  uint uVar2;
  undefined4 uVar3;
  int iVar4;
  uint uVar5;
  undefined8 uVar6;
  long lVar7;
  ulong uVar8;
  undefined8 uVar9;
  undefined8 uVar10;
  long lVar11;
  ulong uVar12;
  code *pcVar13;
  ulong uVar14;
  int iVar15;
  ulong uVar16;
  int *piVar17;
  uint *puVar18;
  uint uVar19;
  long lVar20;
  long *plVar21;
  undefined1 (*pauVar22) [16];
  long lVar23;
  undefined8 *puVar24;
  long lVar25;
  uint uVar26;
  uint uVar27;
  undefined1 auVar28 [16];
  undefined1 auVar29 [12];
  uint uStack_7c;
  uint uStack_74;
  ulong uStack_68;
  
  if ((bRam00000000049f7b15 & 1) == 0) {
    func_0x010c65d4(0x8a6d);
    bRam00000000049f7b15 = 1;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar2 = Util__GetData(0,uVar6,_str_turnIdx,0,0);
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
    auVar28 = ServerTime__get_Now(0,0);
    auVar28 = Util__GetData(0,uVar6,_str_expireTime,auVar28._0_8_,auVar28._8_8_,0);
    uVar8 = auVar28._8_8_;
    lVar20 = *(long *)(param_1 + 0x48);
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = LitJson_JsonData__get_Item(lVar7,_str_lastControlTurns,0);
    if (lVar20 == 0) {
      func_0x010eec64(0);
    }
    BattlePlay_BattlePlayData__DeserializeLastControlTurn(lVar20,uVar6,0);
    if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar20 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
    if (lVar20 == 0) {
      func_0x010eec64(0);
    }
    if ((bRam00000000049f9e17 & 1) == 0) {
      func_0x010c65d4(0x300b);
      bRam00000000049f9e17 = 1;
    }
    uVar6 = *(undefined8 *)(lVar20 + 600);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar12 = UnityEngine_Object__op_Inequality(0,uVar6,0,0);
    if ((uVar12 & 1) != 0) {
      if (*(uint *)(lVar20 + 0x14c) == uVar2) {
        lVar20 = *(long *)(lVar20 + 600);
        if (lVar20 == 0) {
          func_0x010eec64(0);
        }
        if ((bRam00000000049f988e & 1) == 0) {
          func_0x010c65d4(0x20a4);
          bRam00000000049f988e = 1;
        }
        uStack_68 = 0;
        lVar7 = func_0x010fdda8(_class_BattleTimer_<StartTimer>c__AnonStorey0_TypeInfo);
        System_Object___ctor(lVar7,0);
        if (lVar7 == 0) {
          func_0x010eec64(0);
          pauVar22 = &auRam0000000000000010;
          puVar24 = (undefined8 *)(auRam0000000000000010 + 8);
          auRam0000000000000010 = auVar28;
          func_0x010eec64(0);
        }
        else {
          pauVar22 = (undefined1 (*) [16])(lVar7 + 0x10);
          puVar24 = (undefined8 *)(lVar7 + 0x18);
          *pauVar22 = auVar28;
        }
        *(long *)(lVar7 + 0x20) = lVar20;
        uVar6 = *puVar24;
        *(undefined8 *)(lVar20 + 0x28) = *(undefined8 *)*pauVar22;
        *(undefined8 *)(lVar20 + 0x30) = uVar6;
        if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar7 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = SceneDataPipe__get_isMultiplayMode(lVar7,0);
        if ((uVar8 & 1) != 0) {
          if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          uVar8 = Multiplay_MultiplayManager__get_isReplayBattle(0,0);
          if ((uVar8 & 1) != 0) {
            uVar6 = *(undefined8 *)(lVar20 + 0x28);
            uVar9 = *(undefined8 *)(lVar20 + 0x30);
            auVar28 = ServerTime__get_Now(0,0);
            if (((*(byte *)(_class_System_DateTime_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_System_DateTime_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c();
            }
            uStack_68 = System_DateTime__op_Subtraction(0,uVar6,uVar9,auVar28._0_8_,auVar28._8_8_,0)
            ;
            dVar1 = (double)func_0x02d20b30(&uStack_68,0);
            *(float *)(lVar20 + 0x38) = (float)dVar1;
          }
        }
        uVar3 = BattleTimer__get_remainSec(lVar20);
        *(undefined4 *)(lVar20 + 0x58) = uVar3;
        if (((*(byte *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Singleton<SceneDataPipe>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar7 = Singleton<iTweenManager>__get_instance
                          (0,_mm_Method_Singleton<SceneDataPipe>_get_instance__);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = SceneDataPipe__get_isCompetitionMode(lVar7,0);
        if ((uVar8 & 1) == 0) {
          if (((*(byte *)(_class_Singleton<CentralTimer>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<CentralTimer>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar7 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
          uVar9 = UnityEngine_Component__get_gameObject(lVar20,0);
          uVar6 = _mm_Method_BattleTimer_UpdateTime__;
          uVar10 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar10,lVar20,uVar6,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          CentralTimer__RemoveTimer(lVar7,uVar9,uVar10,0);
          lVar7 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
          uVar9 = UnityEngine_Component__get_gameObject(lVar20,0);
          uVar6 = _mm_Method_BattleTimer_UpdateTime__;
          uVar10 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar10,lVar20,uVar6,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          CentralTimer__AddTimer(0x3dcccccd,lVar7,uVar9,uVar10,0xffffffff,0);
        }
        else {
          *(undefined1 *)(lVar20 + 100) = 0;
          if (((*(byte *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar7 = SceneSingleton<WorldBossBattleController>__get_instance
                            (0,_mm_Method_SceneSingleton<MultiplayIndicator>_get_instance__);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          MultiplayIndicator__Off(lVar7,0,0);
          if (((*(byte *)(_class_Singleton<CentralTimer>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Singleton<CentralTimer>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar7 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
          uVar9 = UnityEngine_Component__get_gameObject(lVar20,0);
          uVar6 = _mm_Method_BattleTimer_UpdateTime__;
          uVar10 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar10,lVar20,uVar6,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          CentralTimer__RemoveTimer(lVar7,uVar9,uVar10,0);
          lVar7 = Singleton<iTweenManager>__get_instance
                            (0,_mm_Method_Singleton<CentralTimer>_get_instance__);
          uVar9 = UnityEngine_Component__get_gameObject(lVar20,0);
          uVar6 = _mm_Method_BattleTimer_UpdateTime__;
          uVar10 = func_0x010fdda8(_class_System_Action_TypeInfo);
          System_Action___ctor(uVar10,lVar20,uVar6,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          CentralTimer__AddTimer(0x3dcccccd,lVar7,uVar9,uVar10,0xffffffff,0);
          BattleTimer__UpdateTime(lVar20);
          lVar7 = *(long *)(lVar20 + 0x48);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          lVar7 = UnityEngine_Component__get_gameObject(lVar7,0);
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
          }
          lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar11 == 0) {
            func_0x010eec64(0);
          }
          iVar4 = ControlFlow__get_currentTurnOwner(lVar11,0);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar7,iVar4 == 0,0);
          lVar20 = *(long *)(lVar20 + 0x50);
          if (lVar20 == 0) {
            func_0x010eec64(0);
          }
          lVar20 = UnityEngine_Component__get_gameObject(lVar20,0);
          lVar7 = SceneSingleton<WorldBossBattleController>__get_instance
                            (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          iVar4 = ControlFlow__get_currentTurnOwner(lVar7,0);
          if (lVar20 == 0) {
            func_0x010eec64(0);
          }
          UnityEngine_GameObject__SetActive(lVar20,iVar4 == 2,0);
        }
        return;
      }
      if ((int)*(uint *)(lVar20 + 0x14c) < (int)uVar2) {
        lVar7 = *(long *)(lVar20 + 0x268);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        uVar12 = System_Collections_Generic_Dictionary<int,_DateTime>__ContainsKey
                           (lVar7,uVar2,
                            _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_ContainsKey__
                           );
        lVar20 = *(long *)(lVar20 + 0x268);
        if (lVar20 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_set_Item__;
        lVar7 = _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_Add__;
        if ((uVar12 & 1) == 0) {
          if ((bRam0000000004a07d05 & 1) == 0) {
            func_0x010c65d4(0x380e);
            bRam0000000004a07d05 = 1;
          }
          plVar21 = *(long **)(lVar20 + 0x40);
          if (plVar21 == (long *)0x0) {
            func_0x010eec64(0);
          }
          lVar23 = *(long *)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x118);
          func_0x010bbb2c(lVar23);
          lVar11 = *plVar21;
          if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
            uVar12 = 0;
            piVar17 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
            do {
              if (*(long *)(piVar17 + -2) == lVar23) {
                puVar24 = (undefined8 *)(lVar11 + (ulong)(*piVar17 + 1) * 0x10 + 0x130);
                goto code_r0x03086b04;
              }
              uVar12 = uVar12 + 1;
              piVar17 = piVar17 + 4;
            } while (uVar12 < *(ushort *)(lVar11 + 0x122));
          }
          puVar24 = (undefined8 *)func_0x010bc584(plVar21,lVar23,1);
code_r0x03086b04:
          uVar5 = (*(code *)*puVar24)(plVar21,uVar2,puVar24[1]);
          lVar11 = *(long *)(lVar20 + 0x10);
          if (lVar11 == 0) {
            func_0x010eec64(0);
            lVar11 = *(long *)(lVar20 + 0x10);
            if (lVar11 == 0) {
code_r0x03086f50:
              lVar20 = func_0x010eec64(0);
              lVar7 = *(long *)(lVar20 + 0x10);
              *(undefined4 *)(lVar20 + 0x38) = 0;
              if (lVar7 == 0) {
                func_0x010eec64(0);
              }
              System_Array__Clear(0,lVar7,0,*(undefined4 *)(lVar7 + 0x18),0);
              lVar7 = *(long *)(lVar20 + 0x20);
              if (lVar7 == 0) {
                func_0x010eec64(0);
              }
              System_Array__Clear(0,lVar7,0,*(undefined4 *)(lVar7 + 0x18),0);
              lVar7 = *(long *)(lVar20 + 0x28);
              if (lVar7 == 0) {
                func_0x010eec64(0);
              }
              System_Array__Clear(0,lVar7,0,*(undefined4 *)(lVar7 + 0x18),0);
              lVar7 = *(long *)(lVar20 + 0x18);
              if (lVar7 == 0) {
                func_0x010eec64(0);
              }
              System_Array__Clear(0,lVar7,0,*(undefined4 *)(lVar7 + 0x18),0);
              *(undefined8 *)(lVar20 + 0x30) = 0xffffffff00000000;
              *(int *)(lVar20 + 0x50) = *(int *)(lVar20 + 0x50) + 1;
              return;
            }
            iVar4 = 0;
            if (auRam0000000000000010._8_4_ != 0) {
              iVar4 = (int)(uVar5 & 0x7fffffff) / auRam0000000000000010._8_4_;
            }
            uVar27 = (uVar5 & 0x7fffffff) - iVar4 * auRam0000000000000010._8_4_;
          }
          else {
            iVar4 = *(int *)(lVar11 + 0x18);
            iVar15 = 0;
            if (iVar4 != 0) {
              iVar15 = (int)(uVar5 & 0x7fffffff) / iVar4;
            }
            uVar27 = (uVar5 & 0x7fffffff) - iVar15 * iVar4;
          }
          uStack_74 = uVar5 & 0x7fffffff;
          uStack_68 = uVar8;
          if (*(uint *)(lVar11 + 0x18) <= uVar27) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          iVar4 = *(int *)(lVar11 + (ulong)uVar27 * 4 + 0x20);
          if (iVar4 != 0) {
            uVar19 = iVar4 - 1;
            do {
              lVar11 = *(long *)(lVar20 + 0x18);
              if (lVar11 == 0) {
                func_0x010eec64(0);
              }
              lVar23 = (long)(int)uVar19;
              if (*(uint *)(lVar11 + 0x18) <= uVar19) {
                uVar6 = func_0x010ef850();
                func_0x010eebec(uVar6,0,0);
              }
              if (*(uint *)(lVar11 + lVar23 * 8 + 0x20) == (uVar5 | 0x80000000)) {
                plVar21 = *(long **)(lVar20 + 0x40);
                lVar11 = *(long *)(lVar20 + 0x20);
                if (lVar11 == 0) {
                  func_0x010eec64(0);
                }
                if (*(uint *)(lVar11 + 0x18) <= uVar19) {
                  uVar6 = func_0x010ef850();
                  func_0x010eebec(uVar6,0,0);
                }
                uVar3 = *(undefined4 *)(lVar11 + lVar23 * 4 + 0x20);
                if (plVar21 == (long *)0x0) {
                  func_0x010eec64(0);
                }
                lVar25 = *(long *)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x118);
                func_0x010bbb2c(lVar25);
                lVar11 = *plVar21;
                if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
                  uVar8 = 0;
                  puVar18 = (uint *)(*(long *)(lVar11 + 0xb0) + 8);
                  do {
                    if (*(long *)(puVar18 + -2) == lVar25) {
                      puVar24 = (undefined8 *)(lVar11 + (ulong)*puVar18 * 0x10 + 0x130);
                      goto code_r0x03086c8c;
                    }
                    uVar8 = uVar8 + 1;
                    puVar18 = puVar18 + 4;
                  } while (uVar8 < *(ushort *)(lVar11 + 0x122));
                }
                puVar24 = (undefined8 *)func_0x010bc584(plVar21,lVar25,0);
code_r0x03086c8c:
                uVar8 = (*(code *)*puVar24)(plVar21,uVar3,uVar2,puVar24[1]);
                if ((uVar8 & 1) != 0) {
                  uVar6 = func_0x010fdda8(_class_System_ArgumentException_TypeInfo);
                  System_ArgumentException___ctor
                            (uVar6,
                             _str_An_element_with_the_same_key_already_exists_in_the_dictionary_,0);
                  func_0x010eebec(uVar6,0,
                                  _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_Add__
                                 );
                  func_0x0112148c();
                  goto code_r0x03086f50;
                }
              }
              lVar11 = *(long *)(lVar20 + 0x18);
              if (lVar11 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar11 + 0x18) <= uVar19) {
                uVar6 = func_0x010ef850();
                func_0x010eebec(uVar6,0,0);
              }
              uVar19 = *(uint *)(lVar11 + lVar23 * 8 + 0x24);
            } while (uVar19 != 0xffffffff);
          }
          iVar4 = *(int *)(lVar20 + 0x38) + 1;
          *(int *)(lVar20 + 0x38) = iVar4;
          if (*(int *)(lVar20 + 0x3c) < iVar4) {
            (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar7 + 0x18) + 0xc0) + 0x120))(lVar20);
            lVar7 = *(long *)(lVar20 + 0x10);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            iVar4 = *(int *)(lVar7 + 0x18);
            iVar15 = 0;
            if (iVar4 != 0) {
              iVar15 = (int)uStack_74 / iVar4;
            }
            uVar27 = uStack_74 - iVar15 * iVar4;
          }
          uVar19 = *(uint *)(lVar20 + 0x34);
          if (uVar19 == 0xffffffff) {
            uVar19 = *(uint *)(lVar20 + 0x30);
            *(uint *)(lVar20 + 0x30) = uVar19 + 1;
          }
          else {
            lVar7 = *(long *)(lVar20 + 0x18);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar7 + 0x18) <= uVar19) {
              uVar6 = func_0x010ef850();
              func_0x010eebec(uVar6,0,0);
            }
            *(undefined4 *)(lVar20 + 0x34) = *(undefined4 *)(lVar7 + (long)(int)uVar19 * 8 + 0x24);
          }
          lVar7 = *(long *)(lVar20 + 0x18);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          lVar11 = (long)(int)uVar19;
          if (*(uint *)(lVar7 + 0x18) <= uVar19) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          *(uint *)(lVar7 + lVar11 * 8 + 0x20) = uVar5 | 0x80000000;
          lVar7 = *(long *)(lVar20 + 0x18);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          lVar23 = *(long *)(lVar20 + 0x10);
          if (lVar23 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar23 + 0x18) <= uVar27) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          iVar4 = *(int *)(lVar23 + (long)(int)uVar27 * 4 + 0x20);
          if (*(uint *)(lVar7 + 0x18) <= uVar19) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          *(int *)(lVar7 + lVar11 * 8 + 0x24) = iVar4 + -1;
          lVar7 = *(long *)(lVar20 + 0x10);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar7 + 0x18) <= uVar27) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          *(uint *)(lVar7 + (long)(int)uVar27 * 4 + 0x20) = uVar19 + 1;
          lVar7 = *(long *)(lVar20 + 0x20);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar7 + 0x18) <= uVar19) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          *(uint *)(lVar7 + lVar11 * 4 + 0x20) = uVar2;
          lVar7 = *(long *)(lVar20 + 0x28);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar7 + 0x18) <= uVar19) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          lVar7 = lVar7 + lVar11 * 0x10;
          *(long *)(lVar7 + 0x20) = auVar28._0_8_;
          *(ulong *)(lVar7 + 0x28) = uStack_68;
          *(int *)(lVar20 + 0x50) = *(int *)(lVar20 + 0x50) + 1;
          return;
        }
        if ((bRam0000000004a07d00 & 1) == 0) {
          func_0x010c65d4(0x41cc);
          bRam0000000004a07d00 = 1;
        }
        plVar21 = *(long **)(lVar20 + 0x40);
        if (plVar21 == (long *)0x0) {
          func_0x010eec64(0);
        }
        lVar23 = *(long *)(*(long *)(*(long *)(lVar11 + 0x18) + 0xc0) + 0x118);
        func_0x010bbb2c(lVar23);
        lVar7 = *plVar21;
        if ((ulong)*(ushort *)(lVar7 + 0x122) != 0) {
          uVar12 = 0;
          piVar17 = (int *)(*(long *)(lVar7 + 0xb0) + 8);
          do {
            if (*(long *)(piVar17 + -2) == lVar23) {
              puVar24 = (undefined8 *)(lVar7 + (ulong)(*piVar17 + 1) * 0x10 + 0x130);
              goto code_r0x03085b9c;
            }
            uVar12 = uVar12 + 1;
            piVar17 = piVar17 + 4;
          } while (uVar12 < *(ushort *)(lVar7 + 0x122));
        }
        puVar24 = (undefined8 *)func_0x010bc584(plVar21,lVar23,1);
code_r0x03085b9c:
        uVar12 = puVar24[1];
        uVar5 = (*(code *)*puVar24)(plVar21,(ulong)uVar2);
        lVar7 = *(long *)(lVar20 + 0x10);
        if (lVar7 == 0) {
          func_0x010eec64(0);
          lVar7 = *(long *)(lVar20 + 0x10);
          if (lVar7 == 0) {
code_r0x030860f4:
            auVar29 = func_0x010eec64(0);
            iVar4 = auVar29._8_4_;
            lVar20 = auVar29._0_8_;
            if ((bRam0000000004a07d01 & 1) == 0) {
              func_0x010c65d4(0x3bcc);
              bRam0000000004a07d01 = 1;
            }
            if (iVar4 < 0) {
              uVar6 = func_0x010fdda8(_class_System_ArgumentOutOfRangeException_TypeInfo);
              System_ArgumentOutOfRangeException___ctor(uVar6,_str_capacity,0);
              lVar20 = _mm_Method_System_Collections_Generic_Dictionary<int,_DateTime>_Init__;
              func_0x010eebec(uVar6,0);
              auVar28 = func_0x0112148c();
              lVar7 = auVar28._0_8_;
              uVar8 = auVar28._8_8_ & 0xffffffff;
              if ((bRam0000000004a07d02 & 1) == 0) {
                func_0x010c65d4(0x3b8c);
                bRam0000000004a07d02 = 1;
              }
              uVar6 = func_0x01101ad4(_class_int___TypeInfo,uVar8);
              *(undefined8 *)(lVar7 + 0x10) = uVar6;
              uVar6 = func_0x01101ad4(_class_System_Collections_Generic_Link___TypeInfo,uVar8);
              *(undefined4 *)(lVar7 + 0x34) = 0xffffffff;
              *(undefined8 *)(lVar7 + 0x18) = uVar6;
              uVar6 = *(undefined8 *)(*(long *)(*(long *)(lVar20 + 0x18) + 0xc0) + 0x140);
              func_0x010bbb2c(uVar6);
              uVar6 = func_0x01101ad4(uVar6,uVar8);
              *(undefined8 *)(lVar7 + 0x20) = uVar6;
              uVar6 = *(undefined8 *)(*(long *)(*(long *)(lVar20 + 0x18) + 0xc0) + 0x148);
              func_0x010bbb2c(uVar6);
              uVar6 = func_0x01101ad4(uVar6,uVar8);
              lVar20 = *(long *)(lVar7 + 0x10);
              *(undefined8 *)(lVar7 + 0x28) = uVar6;
              *(undefined4 *)(lVar7 + 0x30) = 0;
              if (lVar20 == 0) {
                func_0x010eec64(0);
              }
              iVar4 = (int)((float)*(int *)(lVar20 + 0x18) * 0.9);
              *(int *)(lVar7 + 0x3c) = iVar4;
              if (iVar4 == 0) {
                lVar20 = *(long *)(lVar7 + 0x10);
                if (lVar20 == 0) {
                  func_0x010eec64(0);
                }
                if (0 < *(int *)(lVar20 + 0x18)) {
                  *(undefined4 *)(lVar7 + 0x3c) = 1;
                }
              }
              return;
            }
            if (uVar12 == 0) {
              lVar7 = *(long *)(*(long *)(*(long *)(uVar8 + 0x18) + 0xc0) + 0x130);
              func_0x010bbb2c(lVar7);
              if (((*(byte *)(lVar7 + 0x12a) & 1) != 0) &&
                 (lVar7 = *(long *)(*(long *)(*(long *)(uVar8 + 0x18) + 0xc0) + 0x130),
                 func_0x010bbb2c(lVar7), *(int *)(lVar7 + 0xd8) == 0)) {
                uVar6 = *(undefined8 *)(*(long *)(*(long *)(uVar8 + 0x18) + 0xc0) + 0x130);
                func_0x010bbb2c(uVar6);
                func_0x010da55c(uVar6);
              }
              uVar12 = (*(code *)**(undefined8 **)
                                   (*(long *)(*(long *)(uVar8 + 0x18) + 0xc0) + 0x128))(0);
            }
            if (lVar20 == 0) {
              func_0x010eec64(0);
            }
            iVar15 = 0xc;
            if (iVar4 != 0) {
              iVar15 = (int)((float)iVar4 / 0.9) + 1;
            }
            *(ulong *)(lVar20 + 0x40) = uVar12;
            if (lVar20 == 0) {
              func_0x010eec64(0);
            }
            (*(code *)**(undefined8 **)(*(long *)(*(long *)(uVar8 + 0x18) + 0xc0) + 0x138))
                      (lVar20,iVar15);
            *(undefined4 *)(lVar20 + 0x50) = 0;
            return;
          }
          iVar4 = 0;
          if (auRam0000000000000010._8_4_ != 0) {
            iVar4 = (int)(uVar5 & 0x7fffffff) / auRam0000000000000010._8_4_;
          }
          uVar27 = (uVar5 & 0x7fffffff) - iVar4 * auRam0000000000000010._8_4_;
        }
        else {
          iVar4 = *(int *)(lVar7 + 0x18);
          iVar15 = 0;
          if (iVar4 != 0) {
            iVar15 = (int)(uVar5 & 0x7fffffff) / iVar4;
          }
          uVar27 = (uVar5 & 0x7fffffff) - iVar15 * iVar4;
        }
        uStack_7c = uVar5 & 0x7fffffff;
        uVar16 = (ulong)uVar27;
        uStack_68 = uVar16;
        if (*(uint *)(lVar7 + 0x18) <= uVar27) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        iVar4 = *(int *)(lVar7 + uVar16 * 4 + 0x20);
        if (iVar4 != 0) {
          uVar27 = iVar4 - 1;
          uVar19 = 0xffffffff;
          do {
            uVar26 = uVar27;
            lVar7 = *(long *)(lVar20 + 0x18);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            lVar23 = (long)(int)uVar26;
            if (*(uint *)(lVar7 + 0x18) <= uVar26) {
              uVar6 = func_0x010ef850();
              func_0x010eebec(uVar6,0,0);
            }
            if (*(uint *)(lVar7 + lVar23 * 8 + 0x20) == (uVar5 | 0x80000000)) {
              plVar21 = *(long **)(lVar20 + 0x40);
              lVar7 = *(long *)(lVar20 + 0x20);
              if (lVar7 == 0) {
                func_0x010eec64(0);
              }
              if (*(uint *)(lVar7 + 0x18) <= uVar26) {
                uVar6 = func_0x010ef850();
                func_0x010eebec(uVar6,0,0);
              }
              uVar3 = *(undefined4 *)(lVar7 + lVar23 * 4 + 0x20);
              if (plVar21 == (long *)0x0) {
                func_0x010eec64(0);
              }
              lVar25 = *(long *)(*(long *)(*(long *)(lVar11 + 0x18) + 0xc0) + 0x118);
              func_0x010bbb2c(lVar25);
              lVar7 = *plVar21;
              if ((ulong)*(ushort *)(lVar7 + 0x122) != 0) {
                uVar8 = 0;
                puVar18 = (uint *)(*(long *)(lVar7 + 0xb0) + 8);
                do {
                  if (*(long *)(puVar18 + -2) == lVar25) {
                    puVar24 = (undefined8 *)(lVar7 + (ulong)*puVar18 * 0x10 + 0x130);
                    goto code_r0x03085d28;
                  }
                  uVar8 = uVar8 + 1;
                  puVar18 = puVar18 + 4;
                } while (uVar8 < *(ushort *)(lVar7 + 0x122));
              }
              puVar24 = (undefined8 *)func_0x010bc584(plVar21,lVar25,0);
code_r0x03085d28:
              uVar8 = puVar24[1];
              uVar12 = (ulong)uVar2;
              uVar14 = (*(code *)*puVar24)(plVar21,uVar3);
              if ((uVar14 & 1) != 0) {
                if (uVar26 != 0xffffffff) {
                  if (uVar19 == 0xffffffff) goto code_r0x03085f54;
                  lVar11 = *(long *)(lVar20 + 0x18);
                  lVar7 = lVar11;
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                    lVar7 = *(long *)(lVar20 + 0x18);
                    if (lVar7 == 0) goto code_r0x030860f4;
                  }
                  if (*(uint *)(lVar7 + 0x18) <= uVar26) {
                    uVar6 = func_0x010ef850();
                    func_0x010eebec(uVar6,0,0);
                  }
                  uVar3 = *(undefined4 *)(lVar7 + lVar23 * 8 + 0x24);
                  if (*(uint *)(lVar11 + 0x18) <= uVar19) {
                    uVar6 = func_0x010ef850();
                    func_0x010eebec(uVar6,0,0);
                  }
                  *(undefined4 *)(lVar11 + (long)(int)uVar19 * 8 + 0x24) = uVar3;
                  lVar7 = *(long *)(lVar20 + 0x18);
                  if (lVar7 == 0) {
                    func_0x010eec64(0);
                  }
                  lVar11 = *(long *)(lVar20 + 0x10);
                  if (lVar11 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar11 + 0x18) <= (uint)uStack_68) {
                    uVar6 = func_0x010ef850();
                    func_0x010eebec(uVar6,0,0);
                  }
                  iVar4 = *(int *)(lVar11 + uVar16 * 4 + 0x20);
                  if (*(uint *)(lVar7 + 0x18) <= uVar26) {
                    uVar6 = func_0x010ef850();
                    func_0x010eebec(uVar6,0,0);
                  }
                  *(int *)(lVar7 + lVar23 * 8 + 0x24) = iVar4 + -1;
                  lVar7 = *(long *)(lVar20 + 0x10);
                  if (lVar7 == 0) {
                    func_0x010eec64(0);
                  }
                  if (*(uint *)(lVar7 + 0x18) <= (uint)uStack_68) {
                    uVar6 = func_0x010ef850();
                    func_0x010eebec(uVar6,0,0);
                  }
                  *(uint *)(lVar7 + uVar16 * 4 + 0x20) = uVar26 + 1;
                  goto code_r0x03085f54;
                }
                break;
              }
            }
            lVar7 = *(long *)(lVar20 + 0x18);
            if (lVar7 == 0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(lVar7 + 0x18) <= uVar26) {
              uVar6 = func_0x010ef850();
              func_0x010eebec(uVar6,0,0);
            }
            uVar27 = *(uint *)(lVar7 + lVar23 * 8 + 0x24);
            uVar19 = uVar26;
          } while (uVar27 != 0xffffffff);
        }
        iVar4 = *(int *)(lVar20 + 0x38) + 1;
        *(int *)(lVar20 + 0x38) = iVar4;
        if (*(int *)(lVar20 + 0x3c) < iVar4) {
          (*(code *)**(undefined8 **)(*(long *)(*(long *)(lVar11 + 0x18) + 0xc0) + 0x120))(lVar20);
          lVar7 = *(long *)(lVar20 + 0x10);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          iVar4 = *(int *)(lVar7 + 0x18);
          iVar15 = 0;
          if (iVar4 != 0) {
            iVar15 = (int)uStack_7c / iVar4;
          }
          uStack_68 = (ulong)(uStack_7c - iVar15 * iVar4);
        }
        uVar26 = *(uint *)(lVar20 + 0x34);
        if (uVar26 == 0xffffffff) {
          uVar26 = *(uint *)(lVar20 + 0x30);
          *(uint *)(lVar20 + 0x30) = uVar26 + 1;
        }
        else {
          lVar7 = *(long *)(lVar20 + 0x18);
          if (lVar7 == 0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(lVar7 + 0x18) <= uVar26) {
            uVar6 = func_0x010ef850();
            func_0x010eebec(uVar6,0,0);
          }
          *(undefined4 *)(lVar20 + 0x34) = *(undefined4 *)(lVar7 + (long)(int)uVar26 * 8 + 0x24);
        }
        lVar7 = *(long *)(lVar20 + 0x18);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        lVar11 = *(long *)(lVar20 + 0x10);
        if (lVar11 == 0) {
          func_0x010eec64(0);
        }
        uVar27 = (uint)uStack_68;
        if (*(uint *)(lVar11 + 0x18) <= uVar27) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        iVar4 = *(int *)(lVar11 + (long)(int)uVar27 * 4 + 0x20);
        lVar11 = (long)(int)uVar26;
        if (*(uint *)(lVar7 + 0x18) <= uVar26) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        *(int *)(lVar7 + lVar11 * 8 + 0x24) = iVar4 + -1;
        lVar7 = *(long *)(lVar20 + 0x10);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar7 + 0x18) <= uVar27) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        *(uint *)(lVar7 + (long)(int)uVar27 * 4 + 0x20) = uVar26 + 1;
        lVar7 = *(long *)(lVar20 + 0x18);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar7 + 0x18) <= uVar26) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        *(uint *)(lVar7 + lVar11 * 8 + 0x20) = uVar5 | 0x80000000;
        lVar7 = *(long *)(lVar20 + 0x20);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar7 + 0x18) <= uVar26) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        *(uint *)(lVar7 + lVar11 * 4 + 0x20) = uVar2;
code_r0x03085f54:
        lVar7 = *(long *)(lVar20 + 0x28);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(lVar7 + 0x18) <= uVar26) {
          uVar6 = func_0x010ef850();
          func_0x010eebec(uVar6,0,0);
        }
        *(undefined1 (*) [16])(lVar7 + (long)(int)uVar26 * 0x10 + 0x20) = auVar28;
        *(int *)(lVar20 + 0x50) = *(int *)(lVar20 + 0x50) + 1;
        return;
      }
    }
    return;
  }
  if ((bRam00000000049f7ad5 & 1) == 0) {
    func_0x010c65d4(0x8a8a);
    bRam00000000049f7ad5 = 1;
  }
  if (*(char *)(param_1 + 0x141) == '\0') {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar2 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar2 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar2 & 1) != 0) {
    return;
  }
  plVar21 = *(long **)(param_1 + 0x40);
  if (plVar21 == (long *)0x0) {
code_r0x013a22a0:
    uVar3 = 8;
  }
  else {
    iVar4 = (**(code **)(*plVar21 + 0x170))(plVar21,*(undefined8 *)(*plVar21 + 0x178));
    if (0 < iVar4) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar20 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar20 != 0
         )) {
        lVar20 = *(long *)(param_1 + 0x48);
        if (lVar20 == 0) {
          func_0x010eec64(0);
        }
        uVar6 = BattlePlay_BattlePlayData__get_battleKey(lVar20,0);
        plVar21 = *(long **)(param_1 + 0x40);
        if (plVar21 == (long *)0x0) {
          uVar3 = 8;
        }
        else {
          uVar3 = (**(code **)(*plVar21 + 0x170))(plVar21,*(undefined8 *)(*plVar21 + 0x178));
        }
        uVar9 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar9,uVar6,1,0,uVar3,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__
                  );
        goto code_r0x013a22d4;
      }
    }
    plVar21 = *(long **)(param_1 + 0x40);
    if (plVar21 == (long *)0x0) goto code_r0x013a22a0;
    uVar3 = (**(code **)(*plVar21 + 0x170))(plVar21,*(undefined8 *)(*plVar21 + 0x178));
  }
  uVar9 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar9,uVar3,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar9 = LitJson_JsonMapper__ToJson(0,uVar9,0);
  uVar6 = _str_cancelMatchmaking;
  uVar8 = MyUserData__get_hasInstance(0,0);
  if ((uVar8 & 1) != 0) {
    lVar20 = MyUserData__get_instance(0,0);
    if (lVar20 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar20 + 0x268) != '\0') {
      lVar20 = *(long *)(param_1 + 0x30);
      if (lVar20 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar6,uVar9,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar6 = System_String__Format(0,_str____0____1__,uVar6,uVar9,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar9 = LitJson_JsonMapper__ToObject(0,uVar6,0);
      lVar11 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar6 = _str__;
      System_Object___ctor(lVar11,0);
      *(undefined4 *)(lVar11 + 0x14) = 2;
      *(undefined4 *)(lVar11 + 0x18) = 0;
      *(undefined8 *)(lVar11 + 0x30) = uVar9;
      *(undefined8 *)(lVar11 + 0x20) = uVar6;
      *(undefined4 *)(lVar11 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar11 + 0x10) = 4;
      lVar7 = lVar20;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar7 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar23 = *(long *)(lVar20 + 0x48);
      if (*(long *)(lVar20 + 0x50) == 0) {
        lVar7 = func_0x010eec64(0);
      }
      uVar6 = SocketIO_Encoder__Encode(lVar7,lVar11);
      if (lVar23 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar23,uVar6,0);
      return;
    }
  }
  uVar8 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar8 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar6 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar8 = UnityEngine_Object__op_Inequality(0,uVar6,0,0);
  if ((uVar8 & 1) == 0) {
    return;
  }
  lVar20 = *(long *)(param_1 + 0x30);
  if (lVar20 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar20,0);
  uVar6 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar6,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar13 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar13 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar13 == (code *)0x0)) {
    uVar9 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar9,0,0);
  }
  pcRam0000000004a04118 = pcVar13;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar6,0);
  return;
}


