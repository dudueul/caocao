// name: BattlePlay.BattlePlayManager$$EmitControl
// entry: 01975e54

/* WARNING: Possible PIC construction at 0x01975ee0: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01975f68: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01975f84: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01975fb8: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01975fdc: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976058: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976144: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976170: Changing call to branch */
/* WARNING: Possible PIC construction at 0x0197618c: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976210: Changing call to branch */
/* WARNING: Possible PIC construction at 0x010eec74: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019760a8: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019760c0: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019760d8: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019760f0: Changing call to branch */
/* WARNING: Removing unreachable block (ram,0x019760c4) */
/* WARNING: Removing unreachable block (ram,0x019760ac) */
/* WARNING: Removing unreachable block (ram,0x019760dc) */
/* WARNING: Removing unreachable block (ram,0x010ef334) */
/* WARNING: Removing unreachable block (ram,0x010ef2b4) */
/* WARNING: Removing unreachable block (ram,0x010ef318) */
/* WARNING: Removing unreachable block (ram,0x010ef298) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong BattlePlay_BattlePlayManager__EmitControl(ulong param_1,ulong param_2)

{
  undefined1 *puVar1;
  char cVar2;
  bool bVar3;
  int iVar4;
  int iVar5;
  undefined4 uVar6;
  undefined4 uVar7;
  uint uVar8;
  uint uVar9;
  long *plVar10;
  undefined8 *puVar11;
  long lVar12;
  ulong uVar13;
  long lVar14;
  undefined8 uVar15;
  ulong uVar16;
  ulong extraout_x1;
  int *piVar17;
  ulong unaff_x19;
  ulong unaff_x20;
  long unaff_x21;
  undefined8 unaff_x22;
  undefined8 uVar18;
  undefined8 uVar19;
  undefined *unaff_x23;
  undefined *unaff_x24;
  undefined *puVar20;
  undefined1 *unaff_x29;
  undefined *unaff_x30;
  undefined1 auVar21 [16];
  undefined4 auStack_b0 [4];
  undefined8 uStack_a0;
  uint uStack_98;
  uint uStack_90;
  int iStack_74;
  undefined8 uStack_70;
  undefined4 uStack_64;
  
  puVar1 = &stack0xfffffffffffffff0;
  if ((bRam00000000049fae05 & 1) == 0) {
    func_0x010c65d4(0x2065);
    bRam00000000049fae05 = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                     (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar12 == 0) {
    unaff_x30 = &UNK_01975ee4;
    register0x00000008 = (BADSPACEBASE *)auStack_b0;
    unaff_x19 = param_2;
    unaff_x20 = param_1;
    unaff_x21 = 0;
    unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
    unaff_x24 = &class_SceneSingleton<ControlFlow>_TypeInfo;
    unaff_x29 = puVar1;
  }
  else {
    uVar13 = ControlFlow__get_isUserMode(lVar12,0);
    if ((uVar13 & 1) != 0) {
      return uVar13;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar13 = Multiplay_MultiplayManager__get_isMyActiveBattle(0,0);
    if ((uVar13 & 1) == 0) {
      return uVar13;
    }
    if (((*(byte *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<GeoMan>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    lVar12 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<GeoMan>_get_instance__);
    if (param_2 == 0) {
      unaff_x30 = &UNK_01975f6c;
      register0x00000008 = (BADSPACEBASE *)auStack_b0;
      unaff_x19 = param_2;
      unaff_x20 = param_1;
      unaff_x21 = lVar12;
      unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
      unaff_x24 = &class_SceneSingleton<ControlFlow>_TypeInfo;
      unaff_x29 = puVar1;
    }
    else {
      lVar14 = nAI_Action__get_bestTile(param_2,0);
      if (lVar14 == 0) {
        unaff_x30 = &UNK_01975f88;
        register0x00000008 = (BADSPACEBASE *)auStack_b0;
        unaff_x19 = param_2;
        unaff_x20 = param_1;
        unaff_x21 = lVar12;
        unaff_x22 = 0;
        unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
        unaff_x24 = &class_SceneSingleton<ControlFlow>_TypeInfo;
        unaff_x29 = puVar1;
      }
      else {
        uVar18 = *(undefined8 *)(lVar14 + 0x24);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        uStack_64 = GeoMan__GetTileIdxByGridPos(lVar12,uVar18,0);
        lVar12 = *(long *)(param_1 + 0x18);
        if (lVar12 == 0) {
          unaff_x30 = &UNK_01975fbc;
          register0x00000008 = (BADSPACEBASE *)auStack_b0;
          unaff_x19 = param_2;
          unaff_x20 = param_1;
          unaff_x21 = lVar12;
          unaff_x22 = uVar18;
          unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
          unaff_x24 = &class_SceneSingleton<ControlFlow>_TypeInfo;
          unaff_x29 = puVar1;
        }
        else {
          uVar13 = *(ulong *)(lVar12 + 0x40);
          if (uVar13 == 0) {
            func_0x010eec64(0);
          }
          uStack_70 = *(undefined8 *)(uVar13 + 0x10);
          if (param_2 == 0) {
            unaff_x30 = &UNK_01975fe0;
            register0x00000008 = (BADSPACEBASE *)auStack_b0;
            unaff_x19 = param_2;
            unaff_x20 = uVar13;
            unaff_x21 = lVar12;
            unaff_x22 = uVar18;
            unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
            unaff_x24 = &class_SceneSingleton<ControlFlow>_TypeInfo;
            unaff_x29 = puVar1;
          }
          else {
            iStack_74 = nAI_Action__get_type(param_2,0);
            if (((*(byte *)(_class_RandomGenerater_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_RandomGenerater_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c(_class_RandomGenerater_TypeInfo);
            }
            uVar13 = RandomGenerater__get_randomIdx(0,0);
            puVar20 = (undefined *)(uVar13 & 0xffffffff);
            if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
               (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
              func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
            }
            uVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                               (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (uVar13 == 0) {
              unaff_x30 = &UNK_0197605c;
              register0x00000008 = (BADSPACEBASE *)auStack_b0;
              unaff_x19 = param_2;
              unaff_x20 = uVar13;
              unaff_x21 = lVar12;
              unaff_x22 = uVar18;
              unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
              unaff_x24 = puVar20;
              unaff_x29 = puVar1;
            }
            else {
              uVar6 = ControlFlow__get_controlBeforeStatus(uVar13,0);
              if (param_2 == 0) {
                unaff_x30 = &UNK_019760ac;
                register0x00000008 = (BADSPACEBASE *)auStack_b0;
                unaff_x19 = param_2;
                unaff_x20 = uVar13;
                unaff_x21 = lVar12;
                unaff_x22 = uVar18;
                unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
                unaff_x24 = puVar20;
                unaff_x29 = puVar1;
              }
              else {
                uVar18 = nAI_Action__get_actor(param_2,0);
                uVar15 = nAI_Action__get_target(param_2,0);
                uVar7 = nAI_Action__get_skillID(param_2,0);
                nAI_Action__get_itemID(param_2,0);
                if (((*(byte *)(_class_IntVector2_TypeInfo + 0x12a) & 1) != 0) &&
                   (*(int *)(_class_IntVector2_TypeInfo + 0xd8) == 0)) {
                  func_0x010da55c();
                }
                uVar19 = *(undefined8 *)(*(long *)(_class_IntVector2_TypeInfo + 0xb8) + 8);
                lVar14 = SceneSingleton<WorldBossBattleController>__get_instance
                                   (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
                if (lVar14 == 0) {
                  unaff_x30 = &UNK_01976148;
                  register0x00000008 = (BADSPACEBASE *)auStack_b0;
                  unaff_x19 = 0;
                  unaff_x20 = uVar13;
                  unaff_x21 = lVar12;
                  unaff_x22 = uVar19;
                  unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
                  unaff_x24 = puVar20;
                  unaff_x29 = puVar1;
                }
                else {
                  uVar8 = ControlFlow__get_currentUnitTurnIdx(lVar14,0);
                  lVar14 = SceneSingleton<WorldBossBattleController>__get_instance
                                     (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
                  if (lVar14 == 0) {
                    unaff_x30 = &UNK_01976174;
                    register0x00000008 = (BADSPACEBASE *)auStack_b0;
                    unaff_x19 = (ulong)uVar8;
                    unaff_x20 = 0;
                    unaff_x21 = lVar12;
                    unaff_x22 = uVar19;
                    unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
                    unaff_x24 = puVar20;
                    unaff_x29 = puVar1;
                  }
                  else {
                    uVar9 = ControlFlow__get_remainedSeconds(lVar14,0);
                    if (lVar12 == 0) {
                      unaff_x30 = &UNK_01976190;
                      register0x00000008 = (BADSPACEBASE *)auStack_b0;
                      unaff_x19 = (ulong)uVar8;
                      unaff_x20 = (ulong)uVar9;
                      unaff_x21 = lVar12;
                      unaff_x22 = uVar19;
                      unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
                      unaff_x24 = puVar20;
                      unaff_x29 = puVar1;
                    }
                    else {
                      auStack_b0[0] = uVar7;
                      uStack_a0 = uVar19;
                      uStack_98 = uVar8;
                      uStack_90 = uVar9;
                      uVar13 = BattlePlay_BattlePlayData__GenerateControl
                                         (lVar12,uStack_70,iStack_74 + 6,puVar20,uVar6,uVar18,uVar15
                                          ,uStack_64);
                      if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0
                          ) && (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0))
                      {
                        func_0x010da55c(_class_Singleton<MultiplayManager>_TypeInfo);
                      }
                      lVar14 = Singleton<iTweenManager>__get_instance
                                         (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
                      if (lVar14 == 0) {
                        unaff_x30 = &UNK_01976214;
                        register0x00000008 = (BADSPACEBASE *)auStack_b0;
                        unaff_x19 = uVar13;
                        unaff_x20 = 0;
                        unaff_x21 = lVar12;
                        unaff_x22 = uVar19;
                        unaff_x23 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
                        unaff_x24 = puVar20;
                        unaff_x29 = puVar1;
                      }
                      else {
                        uVar16 = Multiplay_MultiplayManager__EmitControl(lVar14,uVar13,0,0);
                        if (uVar13 != 0) {
                          return uVar16;
                        }
                      }
                    }
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  *(undefined1 **)((long)register0x00000008 + -0x10) = unaff_x29;
  *(undefined **)((long)register0x00000008 + -8) = unaff_x30;
  *(undefined8 *)((long)register0x00000008 + -0x20) = 0;
  *(undefined8 *)((long)register0x00000008 + -0x18) = 0;
  *(ulong *)((long)register0x00000008 + -0x40) = unaff_x20;
  *(ulong *)((long)register0x00000008 + -0x38) = unaff_x19;
  *(undefined1 **)((long)register0x00000008 + -0x30) =
       (undefined1 *)((long)register0x00000008 + -0x10);
  *(undefined **)((long)register0x00000008 + -0x28) = &UNK_010eec78;
  uVar18 = func_0x010f7dfc();
  auVar21 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac177,
                            (undefined1 *)((long)register0x00000008 + -0x20));
  iVar4 = func_0x010eebec(auVar21._0_8_,auVar21._8_8_,0);
  *(undefined **)((long)register0x00000008 + -0x80) = unaff_x24;
  *(undefined **)((long)register0x00000008 + -0x78) = unaff_x23;
  *(undefined8 *)((long)register0x00000008 + -0x70) = unaff_x22;
  *(long *)((long)register0x00000008 + -0x68) = unaff_x21;
  *(ulong *)((long)register0x00000008 + -0x60) = unaff_x20;
  *(undefined1 **)((long)register0x00000008 + -0x58) =
       (undefined1 *)((long)register0x00000008 + -0x20);
  *(undefined1 **)((long)register0x00000008 + -0x50) =
       (undefined1 *)((long)register0x00000008 + -0x30);
  *(undefined **)((long)register0x00000008 + -0x48) = &UNK_010eecac;
  *(undefined8 *)((long)register0x00000008 + -200) = 0x4970030;
  plVar10 = (long *)func_0x0110dca8();
  if (plVar10 != (long *)0x0) {
    iVar5 = (**(code **)*plVar10)
                      (plVar10,&DAT_036c3b60,(undefined1 *)((long)register0x00000008 + -0xb0));
    if (-1 < iVar5) {
      iVar5 = (**(code **)(**(long **)((long)register0x00000008 + -0xb0) + 0x18))
                        (*(long **)((long)register0x00000008 + -0xb0),
                         (undefined1 *)((long)register0x00000008 + -0xb8));
      (**(code **)(**(long **)((long)register0x00000008 + -0xb0) + 0x10))();
      if ((-1 < iVar5) &&
         (puVar11 = *(undefined8 **)((long)register0x00000008 + -0xb8), puVar11 != (undefined8 *)0x0
         )) {
        iVar5 = (**(code **)*puVar11)
                          (puVar11,&DAT_036c3b40,(undefined1 *)((long)register0x00000008 + -0xc0));
        (**(code **)(**(long **)((long)register0x00000008 + -0xb8) + 0x10))();
        if (-1 < iVar5) {
          uVar13 = (**(code **)(**(long **)((long)register0x00000008 + -0xc0) + 0x18))();
          (**(code **)(**(long **)((long)register0x00000008 + -0xc0) + 0x10))();
          (**(code **)(*plVar10 + 0x10))(plVar10);
          if (uVar13 != 0) goto code_r0x010ef224;
        }
      }
    }
    *(undefined8 *)((long)register0x00000008 + -0xd0) = 0x4970030;
    iVar5 = (**(code **)(*plVar10 + 0x18))
                      (plVar10,(undefined1 *)((long)register0x00000008 + -0x90),
                       (undefined1 *)((long)register0x00000008 + -0x84),
                       (undefined1 *)((long)register0x00000008 + -0x98),
                       (undefined1 *)((long)register0x00000008 + -0xa0));
    if (-1 < iVar5) {
      *(undefined4 *)((long)register0x00000008 + -0xa8) = 0;
      *(undefined4 *)((long)register0x00000008 + -0xa4) = 0;
      if (*(long *)((long)register0x00000008 + -0x90) != 0) {
        func_0x01120ee4(*(long *)((long)register0x00000008 + -0x90),
                        (undefined1 *)((long)register0x00000008 + -0xa4));
      }
      if (*(long *)((long)register0x00000008 + -0x98) != 0) {
        func_0x01120ee4(*(long *)((long)register0x00000008 + -0x98),
                        (undefined1 *)((long)register0x00000008 + -0xa8));
      }
      func_0x01007b60((undefined1 *)((long)register0x00000008 + -0xd0),
                      *(undefined8 *)((long)register0x00000008 + -0x90),
                      (long)*(int *)((long)register0x00000008 + -0xa4));
      if (0 < *(int *)((long)register0x00000008 + -0xa8)) {
        func_0x01007b60((undefined1 *)((long)register0x00000008 + -0xd0),&UNK_036ac50e,1);
        func_0x01007b60((undefined1 *)((long)register0x00000008 + -0xd0),
                        *(undefined8 *)((long)register0x00000008 + -0x98),
                        (long)*(int *)((long)register0x00000008 + -0xa8));
      }
      if (*(long *)((long)register0x00000008 + -0x90) != 0) {
        func_0x01120ef4();
      }
      if (*(long *)((long)register0x00000008 + -0x98) != 0) {
        func_0x01120ef4();
      }
      if (*(long *)((long)register0x00000008 + -0xa0) != 0) {
        func_0x01120ef4();
      }
    }
    func_0x010078c0((undefined1 *)((long)register0x00000008 + -200),
                    (undefined1 *)((long)register0x00000008 + -0xd0));
    if (*(long *)((long)register0x00000008 + -0xd0) != 0x4970030) {
      piVar17 = (int *)(*(long *)((long)register0x00000008 + -0xd0) + -8);
      do {
        iVar5 = *piVar17;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar17,0x10);
        if (bVar3) {
          *piVar17 = iVar5 + -1;
          cVar2 = ExclusiveMonitorsStatus();
        }
      } while (cVar2 != '\0');
      if (iVar5 < 1) {
        func_0x01006b10();
      }
    }
    (**(code **)(*plVar10 + 0x10))(plVar10);
  }
  if (iVar4 < -0x7ff8fff2) {
    switch(iVar4) {
    case -0x7fffbfff:
      uVar18 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0xe0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xd8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac064,
                               (undefined1 *)((long)register0x00000008 + -0xe0));
      break;
    case -0x7fffbffe:
      *(long *)((long)register0x00000008 + -0xf0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xe8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar18 = func_0x010f7dfc();
      uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac149,
                               (undefined1 *)((long)register0x00000008 + -0xf0));
      break;
    case -0x7fffbffd:
      *(long *)((long)register0x00000008 + -0x100) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xf8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar18 = func_0x010f7dfc();
      uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac177,
                               (undefined1 *)((long)register0x00000008 + -0x100));
      break;
    case -0x7fffbffc:
      goto code_r0x010ef004;
    case -0x7fffbffb:
      if (*(long *)(*(long *)((long)register0x00000008 + -200) + -0x18) == 0) {
        func_0x01109d34((undefined1 *)((long)register0x00000008 + -0x168),&UNK_036ac0af);
        func_0x010078c0((undefined1 *)((long)register0x00000008 + -200),
                        (undefined1 *)((long)register0x00000008 + -0x168));
        if (*(long *)((long)register0x00000008 + -0x168) != 0x4970030) {
          piVar17 = (int *)(*(long *)((long)register0x00000008 + -0x168) + -8);
          do {
            iVar4 = *piVar17;
            cVar2 = '\x01';
            bVar3 = (bool)ExclusiveMonitorPass(piVar17,0x10);
            if (bVar3) {
              *piVar17 = iVar4 + -1;
              cVar2 = ExclusiveMonitorsStatus();
            }
          } while (cVar2 != '\0');
          if (iVar4 < 1) {
            func_0x01006b10();
          }
        }
      }
      uVar18 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x178) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x170) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar13 = func_0x010ef4a4(uVar18,&UNK_036ab2da,&UNK_036ac0c1,
                               (undefined1 *)((long)register0x00000008 + -0x178));
      *(undefined4 *)(uVar13 + 0x4c) = 0x80004005;
      break;
    default:
      if (iVar4 != -0x7fffffed) {
        if (iVar4 == -0x7ff8fffb) {
          *(long *)((long)register0x00000008 + -0x120) = *(long *)((long)register0x00000008 + -200);
          *(undefined8 *)((long)register0x00000008 + -0x118) =
               *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
          uVar18 = func_0x010f7dfc();
          uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac36f,
                                   (undefined1 *)((long)register0x00000008 + -0x120));
          break;
        }
        goto code_r0x010ef004;
      }
code_r0x010eefd0:
      uVar18 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x160) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x158) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac097,
                               (undefined1 *)((long)register0x00000008 + -0x160));
      goto code_r0x010ef220;
    }
  }
  else {
    if (iVar4 < -0x7feceac7) {
      if (iVar4 == -0x7ff8fff2) {
        *(long *)((long)register0x00000008 + -0x130) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x128) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar18 = func_0x010f7dfc();
        uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac215,
                                 (undefined1 *)((long)register0x00000008 + -0x130));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7ff8ffa9) {
        *(undefined8 *)((long)register0x00000008 + -0x140) = 0;
        *(undefined8 *)((long)register0x00000008 + -0x138) = 0;
        *(long *)((long)register0x00000008 + -0x150) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x148) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar13 = func_0x010ef4e8((undefined1 *)((long)register0x00000008 + -0x140),
                                 (undefined1 *)((long)register0x00000008 + -0x150));
        goto code_r0x010ef224;
      }
    }
    else {
      if (iVar4 == -0x7feceac7) {
        if (*(long *)(*(long *)((long)register0x00000008 + -200) + -0x18) == 0) {
          func_0x01109d34((undefined1 *)((long)register0x00000008 + -0x180),&UNK_036ac0ce);
          func_0x010078c0((undefined1 *)((long)register0x00000008 + -200),
                          (undefined1 *)((long)register0x00000008 + -0x180));
          if (*(long *)((long)register0x00000008 + -0x180) != 0x4970030) {
            piVar17 = (int *)(*(long *)((long)register0x00000008 + -0x180) + -8);
            do {
              iVar4 = *piVar17;
              cVar2 = '\x01';
              bVar3 = (bool)ExclusiveMonitorPass(piVar17,0x10);
              if (bVar3) {
                *piVar17 = iVar4 + -1;
                cVar2 = ExclusiveMonitorsStatus();
              }
            } while (cVar2 != '\0');
            if (iVar4 < 1) {
              func_0x01006b10();
            }
          }
        }
        *(long *)((long)register0x00000008 + -400) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x188) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar18 = func_0x010f7dfc();
        uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac451,
                                 (undefined1 *)((long)register0x00000008 + -400));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7feceac5) {
        uVar18 = func_0x010f7dfc();
        *(long *)((long)register0x00000008 + -0x110) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x108) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac07c,
                                 (undefined1 *)((long)register0x00000008 + -0x110));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7fece9de) goto code_r0x010eefd0;
    }
code_r0x010ef004:
    if ((extraout_x1 & 1) == 0) {
      uVar18 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x1b0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x1a8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar13 = func_0x010ef4a4(uVar18,&UNK_037aceb6,&UNK_036ac0c4,
                               (undefined1 *)((long)register0x00000008 + -0x1b0));
    }
    else {
      uVar18 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x1a0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x198) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar13 = func_0x010ef4a4(uVar18,&UNK_036ab2da,&UNK_036ac0c1,
                               (undefined1 *)((long)register0x00000008 + -0x1a0));
    }
code_r0x010ef220:
    *(int *)(uVar13 + 0x4c) = iVar4;
  }
code_r0x010ef224:
  if (*(long *)((long)register0x00000008 + -200) != 0x4970030) {
    piVar17 = (int *)(*(long *)((long)register0x00000008 + -200) + -8);
    do {
      iVar4 = *piVar17;
      cVar2 = '\x01';
      bVar3 = (bool)ExclusiveMonitorPass(piVar17,0x10);
      if (bVar3) {
        *piVar17 = iVar4 + -1;
        cVar2 = ExclusiveMonitorsStatus();
      }
    } while (cVar2 != '\0');
    if (iVar4 < 1) {
      func_0x01006b10();
    }
  }
  return uVar13;
}


