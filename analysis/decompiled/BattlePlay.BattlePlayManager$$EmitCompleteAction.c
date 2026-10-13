// name: BattlePlay.BattlePlayManager$$EmitCompleteAction
// entry: 01976270

/* WARNING: Possible PIC construction at 0x01976330: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976350: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019765e0: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976638: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976664: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976680: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019766e0: Changing call to branch */
/* WARNING: Possible PIC construction at 0x010eec74: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019763d8: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976434: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976460: Changing call to branch */
/* WARNING: Possible PIC construction at 0x0197647c: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019764fc: Changing call to branch */
/* WARNING: Removing unreachable block (ram,0x010ef334) */
/* WARNING: Removing unreachable block (ram,0x010ef2b4) */
/* WARNING: Removing unreachable block (ram,0x010ef318) */
/* WARNING: Removing unreachable block (ram,0x010ef298) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong BattlePlay_BattlePlayManager__EmitCompleteAction(undefined *param_1,long param_2,uint param_3)

{
  undefined8 ******ppppppuVar1;
  char cVar2;
  bool bVar3;
  int iVar4;
  int iVar5;
  uint uVar6;
  undefined4 uVar7;
  undefined4 uVar8;
  undefined8 uVar9;
  long *plVar10;
  undefined8 *puVar11;
  ulong uVar12;
  long lVar13;
  long lVar14;
  ulong uVar15;
  ulong extraout_x1;
  int *piVar16;
  ulong unaff_x19;
  ulong uVar17;
  ulong uVar18;
  long unaff_x20;
  ulong unaff_x21;
  undefined *unaff_x22;
  undefined *puVar19;
  undefined *unaff_x23;
  undefined *puVar20;
  ulong unaff_x24;
  ulong uVar21;
  undefined8 ******ppppppuVar22;
  undefined8 ******unaff_x29;
  code *unaff_x30;
  undefined1 auVar23 [16];
  ulong uStack_d0;
  undefined *puStack_c8;
  undefined *puStack_c0;
  ulong uStack_b8;
  long lStack_b0;
  ulong uStack_a8;
  undefined8 *****pppppuStack_a0;
  code *pcStack_98;
  undefined4 auStack_90 [4];
  undefined8 uStack_80;
  undefined4 uStack_78;
  undefined4 uStack_70;
  
  ppppppuVar22 = (undefined8 ******)&stack0xfffffffffffffff0;
  uVar17 = (ulong)param_3;
  if ((bRam00000000049fae06 & 1) == 0) {
    func_0x010c65d4(0x2064);
    bRam00000000049fae06 = 1;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar12 = Multiplay_MultiplayManager__get_isMyActiveBattle(0,0);
  if ((uVar12 & 1) == 0) {
    return uVar12;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                     (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  puVar20 = &class_SceneSingleton<ControlFlow>_TypeInfo;
  if (lVar13 == 0) {
    register0x00000008 = (BADSPACEBASE *)auStack_90;
    unaff_x20 = param_2;
    uVar12 = 0;
    unaff_x22 = param_1;
    unaff_x30 = (code *)&UNK_01976334;
  }
  else {
    uVar6 = ControlFlow__get_currentTurnOwner(lVar13,0);
    uVar21 = *(ulong *)(param_1 + 0x18);
    uVar12 = (ulong)uVar6;
    if (uVar21 == 0) {
      register0x00000008 = (BADSPACEBASE *)auStack_90;
      unaff_x20 = param_2;
      unaff_x22 = param_1;
      unaff_x24 = uVar21;
      unaff_x30 = (code *)&UNK_01976354;
    }
    else {
      if (uVar6 == 0) {
        lVar13 = *(long *)(uVar21 + 0x40);
      }
      else {
        lVar13 = *(long *)(uVar21 + 0x48);
      }
      if (lVar13 == 0) {
        auVar23 = func_0x010eec64(0);
        puStack_c8 = &class_SceneSingleton<ControlFlow>_TypeInfo;
        pcStack_98 = BattlePlay_BattlePlayManager__EmitSticker;
        ppppppuVar1 = &pppppuStack_a0;
        uVar18 = auVar23._8_8_ & 0xffffffff;
        uStack_d0 = uVar21;
        puStack_c0 = param_1;
        uStack_b8 = uVar12;
        lStack_b0 = param_2;
        uStack_a8 = uVar17;
        pppppuStack_a0 = ppppppuVar22;
        if ((bRam00000000049fae07 & 1) == 0) {
          func_0x010c65d4(0x2067);
          bRam00000000049fae07 = 1;
        }
        if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar17 = Multiplay_MultiplayManager__get_isMyActiveBattle(0,0);
        if ((uVar17 & 1) == 0) {
          return uVar17;
        }
        puVar20 = *(undefined **)(auVar23._0_8_ + 0x18);
        uVar17 = uVar18;
        ppppppuVar22 = ppppppuVar1;
        if (puVar20 == (undefined *)0x0) {
          register0x00000008 = (BADSPACEBASE *)&uStack_d0;
          unaff_x20 = auVar23._0_8_;
          uVar12 = 0x49fa000;
          unaff_x22 = param_1;
          unaff_x24 = uVar21;
          unaff_x30 = (code *)&UNK_019765e4;
        }
        else {
          lVar13 = *(long *)(puVar20 + 0x40);
          if (lVar13 == 0) {
            func_0x010eec64(0);
          }
          unaff_x20 = *(long *)(lVar13 + 0x10);
          if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c();
          }
          lVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar13 == 0) {
            register0x00000008 = (BADSPACEBASE *)&uStack_d0;
            uVar12 = 0;
            unaff_x22 = &mm_Method_SceneSingleton<ControlFlow>_get_instance__;
            unaff_x24 = uVar21;
            unaff_x30 = (code *)&UNK_0197663c;
          }
          else {
            uVar12 = ControlFlow__get_currentUnitTurnIdx(lVar13,0);
            uVar12 = uVar12 & 0xffffffff;
            lVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                               (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar13 == 0) {
              register0x00000008 = (BADSPACEBASE *)&uStack_d0;
              unaff_x22 = (undefined *)0x0;
              unaff_x24 = uVar21;
              unaff_x30 = (code *)&UNK_01976668;
            }
            else {
              uVar15 = ControlFlow__get_remainedSeconds(lVar13,0);
              unaff_x22 = (undefined *)(uVar15 & 0xffffffff);
              if (puVar20 == (undefined *)0x0) {
                register0x00000008 = (BADSPACEBASE *)&uStack_d0;
                unaff_x24 = uVar21;
                unaff_x30 = (code *)&UNK_01976684;
              }
              else {
                uVar17 = BattlePlay_BattlePlayData__GenerateSticker
                                   (uVar15,unaff_x20,uVar18,uVar12,unaff_x22);
                if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
                   (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
                  func_0x010da55c(_class_Singleton<MultiplayManager>_TypeInfo);
                }
                lVar13 = Singleton<iTweenManager>__get_instance
                                   (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
                if (lVar13 == 0) {
                  register0x00000008 = (BADSPACEBASE *)&uStack_d0;
                  unaff_x20 = 0;
                  unaff_x24 = uVar21;
                  unaff_x30 = (code *)&UNK_019766e4;
                }
                else {
                  uVar12 = Multiplay_MultiplayManager__EmitSticker(lVar13,uVar17,0);
                  if (uVar17 != 0) {
                    return uVar12;
                  }
                  register0x00000008 = (BADSPACEBASE *)auStack_90;
                  uVar17 = uStack_a8;
                  unaff_x20 = lStack_b0;
                  uVar12 = uStack_b8;
                  unaff_x22 = puStack_c0;
                  puVar20 = puStack_c8;
                  unaff_x24 = uStack_d0;
                  ppppppuVar22 = (undefined8 ******)pppppuStack_a0;
                  unaff_x30 = pcStack_98;
                }
              }
            }
          }
        }
      }
      else {
        uVar12 = *(ulong *)(lVar13 + 0x10);
        puVar19 = *(undefined **)(param_1 + 0x18);
        if (((*(byte *)(_class_RandomGenerater_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_RandomGenerater_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        uVar21 = RandomGenerater__get_randomIdx(0,0);
        puVar20 = (undefined *)(uVar21 & 0xffffffff);
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_SceneSingleton<ControlFlow>_TypeInfo);
        }
        lVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar13 == 0) {
          unaff_x30 = (code *)&UNK_019763dc;
          register0x00000008 = (BADSPACEBASE *)auStack_90;
          unaff_x20 = param_2;
          unaff_x22 = puVar19;
          unaff_x24 = 0;
        }
        else {
          uVar21 = ControlFlow__get_controlStatus(lVar13,0);
          uVar21 = uVar21 & 0xffffffff;
          if (((*(byte *)(_class_IntVector2_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_IntVector2_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_IntVector2_TypeInfo);
          }
          uVar9 = *(undefined8 *)(*(long *)(_class_IntVector2_TypeInfo + 0xb8) + 8);
          lVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar13 == 0) {
            unaff_x30 = (code *)&UNK_01976438;
            register0x00000008 = (BADSPACEBASE *)auStack_90;
            unaff_x20 = param_2;
            unaff_x22 = puVar19;
            unaff_x24 = uVar21;
          }
          else {
            uVar7 = ControlFlow__get_currentUnitTurnIdx(lVar13,0);
            lVar13 = SceneSingleton<WorldBossBattleController>__get_instance
                               (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
            if (lVar13 == 0) {
              unaff_x30 = (code *)&UNK_01976464;
              register0x00000008 = (BADSPACEBASE *)auStack_90;
              unaff_x20 = param_2;
              unaff_x22 = puVar19;
              unaff_x24 = uVar21;
            }
            else {
              uVar8 = ControlFlow__get_remainedSeconds(lVar13,0);
              if (puVar19 == (undefined *)0x0) {
                unaff_x30 = (code *)&UNK_01976480;
                register0x00000008 = (BADSPACEBASE *)auStack_90;
                unaff_x20 = param_2;
                unaff_x22 = puVar19;
                unaff_x24 = uVar21;
              }
              else {
                auStack_90[0] = 0;
                uStack_80 = uVar9;
                uStack_78 = uVar7;
                uStack_70 = uVar8;
                lVar13 = BattlePlay_BattlePlayData__GenerateControl
                                   (puVar19,uVar12,10,puVar20,uVar21,param_2,0,0);
                if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
                   (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
                  func_0x010da55c(_class_Singleton<MultiplayManager>_TypeInfo);
                }
                lVar14 = Singleton<iTweenManager>__get_instance
                                   (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
                if (lVar14 == 0) {
                  unaff_x30 = (code *)&UNK_01976500;
                  uVar12 = 0;
                  register0x00000008 = (BADSPACEBASE *)auStack_90;
                  unaff_x20 = lVar13;
                  unaff_x22 = puVar19;
                  unaff_x24 = uVar21;
                }
                else {
                  uVar21 = Multiplay_MultiplayManager__EmitControl(lVar14,lVar13,param_3 & 1,0);
                  uVar17 = unaff_x19;
                  uVar12 = unaff_x21;
                  puVar20 = unaff_x23;
                  ppppppuVar22 = unaff_x29;
                  if (lVar13 != 0) {
                    return uVar21;
                  }
                }
              }
            }
          }
        }
      }
    }
  }
  *(undefined8 *******)((long)register0x00000008 + -0x10) = ppppppuVar22;
  *(code **)((long)register0x00000008 + -8) = unaff_x30;
  *(undefined8 *)((long)register0x00000008 + -0x20) = 0;
  *(undefined8 *)((long)register0x00000008 + -0x18) = 0;
  *(long *)((long)register0x00000008 + -0x40) = unaff_x20;
  *(ulong *)((long)register0x00000008 + -0x38) = uVar17;
  *(undefined1 **)((long)register0x00000008 + -0x30) =
       (undefined1 *)((long)register0x00000008 + -0x10);
  *(undefined **)((long)register0x00000008 + -0x28) = &UNK_010eec78;
  uVar9 = func_0x010f7dfc();
  auVar23 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac177,
                            (undefined1 *)((long)register0x00000008 + -0x20));
  iVar4 = func_0x010eebec(auVar23._0_8_,auVar23._8_8_,0);
  *(ulong *)((long)register0x00000008 + -0x80) = unaff_x24;
  *(undefined **)((long)register0x00000008 + -0x78) = puVar20;
  *(undefined **)((long)register0x00000008 + -0x70) = unaff_x22;
  *(ulong *)((long)register0x00000008 + -0x68) = uVar12;
  *(long *)((long)register0x00000008 + -0x60) = unaff_x20;
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
          uVar17 = (**(code **)(**(long **)((long)register0x00000008 + -0xc0) + 0x18))();
          (**(code **)(**(long **)((long)register0x00000008 + -0xc0) + 0x10))();
          (**(code **)(*plVar10 + 0x10))(plVar10);
          if (uVar17 != 0) goto code_r0x010ef224;
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
      piVar16 = (int *)(*(long *)((long)register0x00000008 + -0xd0) + -8);
      do {
        iVar5 = *piVar16;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar16,0x10);
        if (bVar3) {
          *piVar16 = iVar5 + -1;
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
      uVar9 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0xe0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xd8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac064,
                               (undefined1 *)((long)register0x00000008 + -0xe0));
      break;
    case -0x7fffbffe:
      *(long *)((long)register0x00000008 + -0xf0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xe8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar9 = func_0x010f7dfc();
      uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac149,
                               (undefined1 *)((long)register0x00000008 + -0xf0));
      break;
    case -0x7fffbffd:
      *(long *)((long)register0x00000008 + -0x100) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xf8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar9 = func_0x010f7dfc();
      uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac177,
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
          piVar16 = (int *)(*(long *)((long)register0x00000008 + -0x168) + -8);
          do {
            iVar4 = *piVar16;
            cVar2 = '\x01';
            bVar3 = (bool)ExclusiveMonitorPass(piVar16,0x10);
            if (bVar3) {
              *piVar16 = iVar4 + -1;
              cVar2 = ExclusiveMonitorsStatus();
            }
          } while (cVar2 != '\0');
          if (iVar4 < 1) {
            func_0x01006b10();
          }
        }
      }
      uVar9 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x178) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x170) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar17 = func_0x010ef4a4(uVar9,&UNK_036ab2da,&UNK_036ac0c1,
                               (undefined1 *)((long)register0x00000008 + -0x178));
      *(undefined4 *)(uVar17 + 0x4c) = 0x80004005;
      break;
    default:
      if (iVar4 != -0x7fffffed) {
        if (iVar4 == -0x7ff8fffb) {
          *(long *)((long)register0x00000008 + -0x120) = *(long *)((long)register0x00000008 + -200);
          *(undefined8 *)((long)register0x00000008 + -0x118) =
               *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
          uVar9 = func_0x010f7dfc();
          uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac36f,
                                   (undefined1 *)((long)register0x00000008 + -0x120));
          break;
        }
        goto code_r0x010ef004;
      }
code_r0x010eefd0:
      uVar9 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x160) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x158) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac097,
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
        uVar9 = func_0x010f7dfc();
        uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac215,
                                 (undefined1 *)((long)register0x00000008 + -0x130));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7ff8ffa9) {
        *(undefined8 *)((long)register0x00000008 + -0x140) = 0;
        *(undefined8 *)((long)register0x00000008 + -0x138) = 0;
        *(long *)((long)register0x00000008 + -0x150) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x148) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar17 = func_0x010ef4e8((undefined1 *)((long)register0x00000008 + -0x140),
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
            piVar16 = (int *)(*(long *)((long)register0x00000008 + -0x180) + -8);
            do {
              iVar4 = *piVar16;
              cVar2 = '\x01';
              bVar3 = (bool)ExclusiveMonitorPass(piVar16,0x10);
              if (bVar3) {
                *piVar16 = iVar4 + -1;
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
        uVar9 = func_0x010f7dfc();
        uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac451,
                                 (undefined1 *)((long)register0x00000008 + -400));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7feceac5) {
        uVar9 = func_0x010f7dfc();
        *(long *)((long)register0x00000008 + -0x110) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x108) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac07c,
                                 (undefined1 *)((long)register0x00000008 + -0x110));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7fece9de) goto code_r0x010eefd0;
    }
code_r0x010ef004:
    if ((extraout_x1 & 1) == 0) {
      uVar9 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x1b0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x1a8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar17 = func_0x010ef4a4(uVar9,&UNK_037aceb6,&UNK_036ac0c4,
                               (undefined1 *)((long)register0x00000008 + -0x1b0));
    }
    else {
      uVar9 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x1a0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x198) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar17 = func_0x010ef4a4(uVar9,&UNK_036ab2da,&UNK_036ac0c1,
                               (undefined1 *)((long)register0x00000008 + -0x1a0));
    }
code_r0x010ef220:
    *(int *)(uVar17 + 0x4c) = iVar4;
  }
code_r0x010ef224:
  if (*(long *)((long)register0x00000008 + -200) != 0x4970030) {
    piVar16 = (int *)(*(long *)((long)register0x00000008 + -200) + -8);
    do {
      iVar4 = *piVar16;
      cVar2 = '\x01';
      bVar3 = (bool)ExclusiveMonitorPass(piVar16,0x10);
      if (bVar3) {
        *piVar16 = iVar4 + -1;
        cVar2 = ExclusiveMonitorsStatus();
      }
    } while (cVar2 != '\0');
    if (iVar4 < 1) {
      func_0x01006b10();
    }
  }
  return uVar17;
}


