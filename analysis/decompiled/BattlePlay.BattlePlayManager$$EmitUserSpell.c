// name: BattlePlay.BattlePlayManager$$EmitUserSpell
// entry: 01976728

/* WARNING: Possible PIC construction at 0x019767ac: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976868: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976894: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976908: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976934: Changing call to branch */
/* WARNING: Possible PIC construction at 0x01976950: Changing call to branch */
/* WARNING: Possible PIC construction at 0x019769c4: Changing call to branch */
/* WARNING: Possible PIC construction at 0x010eec74: Changing call to branch */
/* WARNING: Removing unreachable block (ram,0x010ef334) */
/* WARNING: Removing unreachable block (ram,0x010ef2b4) */
/* WARNING: Removing unreachable block (ram,0x010ef318) */
/* WARNING: Removing unreachable block (ram,0x010ef298) */
/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

ulong BattlePlay_BattlePlayManager__EmitUserSpell(long param_1)

{
  undefined1 *puVar1;
  char cVar2;
  bool bVar3;
  int iVar4;
  int iVar5;
  undefined4 uVar6;
  long *plVar7;
  undefined8 *puVar8;
  long lVar9;
  ulong uVar10;
  long lVar11;
  undefined8 uVar12;
  ulong uVar13;
  ulong extraout_x1;
  int *piVar14;
  long unaff_x19;
  ulong unaff_x20;
  undefined8 unaff_x21;
  undefined *unaff_x22;
  undefined *puVar15;
  undefined *unaff_x23;
  undefined *puVar16;
  ulong unaff_x24;
  undefined1 *unaff_x29;
  undefined *unaff_x30;
  undefined1 auVar17 [16];
  undefined4 auStack_60 [4];
  
  puVar1 = &stack0xfffffffffffffff0;
  if ((bRam00000000049fae08 & 1) == 0) {
    func_0x010c65d4(0x2068);
    bRam00000000049fae08 = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar9 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
  if (lVar9 == 0) {
    unaff_x30 = &UNK_019767b0;
    register0x00000008 = (BADSPACEBASE *)auStack_60;
    unaff_x19 = param_1;
    unaff_x20 = 0;
    unaff_x23 = &class_SceneSingleton<ControlFlow>_TypeInfo;
    unaff_x29 = puVar1;
  }
  else {
    uVar10 = ControlFlow__get_isUserMode(lVar9,0);
    if ((uVar10 & 1) == 0) {
      return uVar10;
    }
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar10 = Multiplay_MultiplayManager__get_isMyActiveBattle(0,0);
    if ((uVar10 & 1) == 0) {
      return uVar10;
    }
    lVar9 = *(long *)(param_1 + 0x18);
    if (((*(byte *)(_class_RandomGenerater_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_RandomGenerater_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar10 = RandomGenerater__get_randomIdx(0,0);
    uVar10 = uVar10 & 0xffffffff;
    if (((*(byte *)(_class_SceneSingleton<UserSpellAction>_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_SceneSingleton<UserSpellAction>_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_SceneSingleton<UserSpellAction>_TypeInfo);
    }
    lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                       (0,_mm_Method_SceneSingleton<UserSpellAction>_get_instance__);
    if (lVar11 == 0) {
      unaff_x30 = &UNK_0197686c;
      register0x00000008 = (BADSPACEBASE *)auStack_60;
      unaff_x19 = lVar9;
      unaff_x20 = uVar10;
      unaff_x21 = 0;
      unaff_x22 = &mm_Method_SceneSingleton<UserSpellAction>_get_instance__;
      unaff_x23 = &class_SceneSingleton<ControlFlow>_TypeInfo;
      unaff_x29 = puVar1;
    }
    else {
      uVar12 = UserSpellAction__GetTargetUnits(lVar11,0);
      lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                         (0,_mm_Method_SceneSingleton<UserSpellAction>_get_instance__);
      if (lVar11 == 0) {
        unaff_x30 = &UNK_01976898;
        register0x00000008 = (BADSPACEBASE *)auStack_60;
        unaff_x19 = lVar9;
        unaff_x20 = uVar10;
        unaff_x21 = uVar12;
        unaff_x22 = (undefined *)0x0;
        unaff_x23 = &class_SceneSingleton<ControlFlow>_TypeInfo;
        unaff_x29 = puVar1;
      }
      else {
        uVar13 = UserSpellAction__GetActionSpellId(lVar11,0);
        puVar15 = (undefined *)(uVar13 & 0xffffffff);
        if (((*(byte *)(_class_IntVector2_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_IntVector2_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c(_class_IntVector2_TypeInfo);
        }
        puVar16 = *(undefined **)(*(long *)(_class_IntVector2_TypeInfo + 0xb8) + 8);
        if (((*(byte *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0x12a) & 1) != 0) &&
           (*(int *)(_class_SceneSingleton<ControlFlow>_TypeInfo + 0xd8) == 0)) {
          func_0x010da55c();
        }
        lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                           (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
        if (lVar11 == 0) {
          unaff_x30 = &UNK_0197690c;
          register0x00000008 = (BADSPACEBASE *)auStack_60;
          unaff_x19 = lVar9;
          unaff_x20 = uVar10;
          unaff_x21 = uVar12;
          unaff_x22 = puVar15;
          unaff_x23 = puVar16;
          unaff_x24 = 0;
          unaff_x29 = puVar1;
        }
        else {
          uVar13 = ControlFlow__get_currentUnitTurnIdx(lVar11,0);
          uVar13 = uVar13 & 0xffffffff;
          lVar11 = SceneSingleton<WorldBossBattleController>__get_instance
                             (0,_mm_Method_SceneSingleton<ControlFlow>_get_instance__);
          if (lVar11 == 0) {
            unaff_x30 = &UNK_01976938;
            register0x00000008 = (BADSPACEBASE *)auStack_60;
            unaff_x19 = lVar9;
            unaff_x20 = uVar10;
            unaff_x21 = uVar12;
            unaff_x22 = puVar15;
            unaff_x23 = puVar16;
            unaff_x24 = uVar13;
            unaff_x29 = puVar1;
          }
          else {
            uVar6 = ControlFlow__get_remainedSeconds(lVar11,0);
            if (lVar9 == 0) {
              unaff_x30 = &UNK_01976954;
              register0x00000008 = (BADSPACEBASE *)auStack_60;
              unaff_x19 = lVar9;
              unaff_x20 = uVar10;
              unaff_x21 = uVar12;
              unaff_x22 = puVar15;
              unaff_x23 = puVar16;
              unaff_x24 = uVar13;
              unaff_x29 = puVar1;
            }
            else {
              auStack_60[0] = uVar6;
              lVar9 = BattlePlay_BattlePlayData__GenerateUserSpellControl
                                (lVar9,uVar10,0,uVar12,0,puVar15,puVar16,uVar13);
              if (((*(byte *)(_class_Singleton<MultiplayManager>_TypeInfo + 0x12a) & 1) != 0) &&
                 (*(int *)(_class_Singleton<MultiplayManager>_TypeInfo + 0xd8) == 0)) {
                func_0x010da55c(_class_Singleton<MultiplayManager>_TypeInfo);
              }
              lVar11 = Singleton<iTweenManager>__get_instance
                                 (0,_mm_Method_Singleton<MultiplayManager>_get_instance__);
              if (lVar11 == 0) {
                unaff_x30 = &UNK_019769c8;
                unaff_x20 = 0;
                register0x00000008 = (BADSPACEBASE *)auStack_60;
                unaff_x19 = lVar9;
                unaff_x21 = uVar12;
                unaff_x22 = puVar15;
                unaff_x23 = puVar16;
                unaff_x24 = uVar13;
                unaff_x29 = puVar1;
              }
              else {
                uVar10 = Multiplay_MultiplayManager__EmitControl(lVar11,lVar9,0,0);
                if (lVar9 != 0) {
                  return uVar10;
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
  *(long *)((long)register0x00000008 + -0x38) = unaff_x19;
  *(undefined1 **)((long)register0x00000008 + -0x30) =
       (undefined1 *)((long)register0x00000008 + -0x10);
  *(undefined **)((long)register0x00000008 + -0x28) = &UNK_010eec78;
  uVar12 = func_0x010f7dfc();
  auVar17 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac177,
                            (undefined1 *)((long)register0x00000008 + -0x20));
  iVar4 = func_0x010eebec(auVar17._0_8_,auVar17._8_8_,0);
  *(ulong *)((long)register0x00000008 + -0x80) = unaff_x24;
  *(undefined **)((long)register0x00000008 + -0x78) = unaff_x23;
  *(undefined **)((long)register0x00000008 + -0x70) = unaff_x22;
  *(undefined8 *)((long)register0x00000008 + -0x68) = unaff_x21;
  *(ulong *)((long)register0x00000008 + -0x60) = unaff_x20;
  *(undefined1 **)((long)register0x00000008 + -0x58) =
       (undefined1 *)((long)register0x00000008 + -0x20);
  *(undefined1 **)((long)register0x00000008 + -0x50) =
       (undefined1 *)((long)register0x00000008 + -0x30);
  *(undefined **)((long)register0x00000008 + -0x48) = &UNK_010eecac;
  *(undefined8 *)((long)register0x00000008 + -200) = 0x4970030;
  plVar7 = (long *)func_0x0110dca8();
  if (plVar7 != (long *)0x0) {
    iVar5 = (**(code **)*plVar7)
                      (plVar7,&DAT_036c3b60,(undefined1 *)((long)register0x00000008 + -0xb0));
    if (-1 < iVar5) {
      iVar5 = (**(code **)(**(long **)((long)register0x00000008 + -0xb0) + 0x18))
                        (*(long **)((long)register0x00000008 + -0xb0),
                         (undefined1 *)((long)register0x00000008 + -0xb8));
      (**(code **)(**(long **)((long)register0x00000008 + -0xb0) + 0x10))();
      if ((-1 < iVar5) &&
         (puVar8 = *(undefined8 **)((long)register0x00000008 + -0xb8), puVar8 != (undefined8 *)0x0))
      {
        iVar5 = (**(code **)*puVar8)
                          (puVar8,&DAT_036c3b40,(undefined1 *)((long)register0x00000008 + -0xc0));
        (**(code **)(**(long **)((long)register0x00000008 + -0xb8) + 0x10))();
        if (-1 < iVar5) {
          uVar10 = (**(code **)(**(long **)((long)register0x00000008 + -0xc0) + 0x18))();
          (**(code **)(**(long **)((long)register0x00000008 + -0xc0) + 0x10))();
          (**(code **)(*plVar7 + 0x10))(plVar7);
          if (uVar10 != 0) goto code_r0x010ef224;
        }
      }
    }
    *(undefined8 *)((long)register0x00000008 + -0xd0) = 0x4970030;
    iVar5 = (**(code **)(*plVar7 + 0x18))
                      (plVar7,(undefined1 *)((long)register0x00000008 + -0x90),
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
      piVar14 = (int *)(*(long *)((long)register0x00000008 + -0xd0) + -8);
      do {
        iVar5 = *piVar14;
        cVar2 = '\x01';
        bVar3 = (bool)ExclusiveMonitorPass(piVar14,0x10);
        if (bVar3) {
          *piVar14 = iVar5 + -1;
          cVar2 = ExclusiveMonitorsStatus();
        }
      } while (cVar2 != '\0');
      if (iVar5 < 1) {
        func_0x01006b10();
      }
    }
    (**(code **)(*plVar7 + 0x10))(plVar7);
  }
  if (iVar4 < -0x7ff8fff2) {
    switch(iVar4) {
    case -0x7fffbfff:
      uVar12 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0xe0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xd8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac064,
                               (undefined1 *)((long)register0x00000008 + -0xe0));
      break;
    case -0x7fffbffe:
      *(long *)((long)register0x00000008 + -0xf0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xe8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar12 = func_0x010f7dfc();
      uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac149,
                               (undefined1 *)((long)register0x00000008 + -0xf0));
      break;
    case -0x7fffbffd:
      *(long *)((long)register0x00000008 + -0x100) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0xf8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar12 = func_0x010f7dfc();
      uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac177,
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
          piVar14 = (int *)(*(long *)((long)register0x00000008 + -0x168) + -8);
          do {
            iVar4 = *piVar14;
            cVar2 = '\x01';
            bVar3 = (bool)ExclusiveMonitorPass(piVar14,0x10);
            if (bVar3) {
              *piVar14 = iVar4 + -1;
              cVar2 = ExclusiveMonitorsStatus();
            }
          } while (cVar2 != '\0');
          if (iVar4 < 1) {
            func_0x01006b10();
          }
        }
      }
      uVar12 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x178) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x170) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar10 = func_0x010ef4a4(uVar12,&UNK_036ab2da,&UNK_036ac0c1,
                               (undefined1 *)((long)register0x00000008 + -0x178));
      *(undefined4 *)(uVar10 + 0x4c) = 0x80004005;
      break;
    default:
      if (iVar4 != -0x7fffffed) {
        if (iVar4 == -0x7ff8fffb) {
          *(long *)((long)register0x00000008 + -0x120) = *(long *)((long)register0x00000008 + -200);
          *(undefined8 *)((long)register0x00000008 + -0x118) =
               *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
          uVar12 = func_0x010f7dfc();
          uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac36f,
                                   (undefined1 *)((long)register0x00000008 + -0x120));
          break;
        }
        goto code_r0x010ef004;
      }
code_r0x010eefd0:
      uVar12 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x160) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x158) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac097,
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
        uVar12 = func_0x010f7dfc();
        uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac215,
                                 (undefined1 *)((long)register0x00000008 + -0x130));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7ff8ffa9) {
        *(undefined8 *)((long)register0x00000008 + -0x140) = 0;
        *(undefined8 *)((long)register0x00000008 + -0x138) = 0;
        *(long *)((long)register0x00000008 + -0x150) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x148) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar10 = func_0x010ef4e8((undefined1 *)((long)register0x00000008 + -0x140),
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
            piVar14 = (int *)(*(long *)((long)register0x00000008 + -0x180) + -8);
            do {
              iVar4 = *piVar14;
              cVar2 = '\x01';
              bVar3 = (bool)ExclusiveMonitorPass(piVar14,0x10);
              if (bVar3) {
                *piVar14 = iVar4 + -1;
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
        uVar12 = func_0x010f7dfc();
        uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac451,
                                 (undefined1 *)((long)register0x00000008 + -400));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7feceac5) {
        uVar12 = func_0x010f7dfc();
        *(long *)((long)register0x00000008 + -0x110) = *(long *)((long)register0x00000008 + -200);
        *(undefined8 *)((long)register0x00000008 + -0x108) =
             *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
        uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac07c,
                                 (undefined1 *)((long)register0x00000008 + -0x110));
        goto code_r0x010ef224;
      }
      if (iVar4 == -0x7fece9de) goto code_r0x010eefd0;
    }
code_r0x010ef004:
    if ((extraout_x1 & 1) == 0) {
      uVar12 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x1b0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x1a8) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar10 = func_0x010ef4a4(uVar12,&UNK_037aceb6,&UNK_036ac0c4,
                               (undefined1 *)((long)register0x00000008 + -0x1b0));
    }
    else {
      uVar12 = func_0x010f7dfc();
      *(long *)((long)register0x00000008 + -0x1a0) = *(long *)((long)register0x00000008 + -200);
      *(undefined8 *)((long)register0x00000008 + -0x198) =
           *(undefined8 *)(*(long *)((long)register0x00000008 + -200) + -0x18);
      uVar10 = func_0x010ef4a4(uVar12,&UNK_036ab2da,&UNK_036ac0c1,
                               (undefined1 *)((long)register0x00000008 + -0x1a0));
    }
code_r0x010ef220:
    *(int *)(uVar10 + 0x4c) = iVar4;
  }
code_r0x010ef224:
  if (*(long *)((long)register0x00000008 + -200) != 0x4970030) {
    piVar14 = (int *)(*(long *)((long)register0x00000008 + -200) + -8);
    do {
      iVar4 = *piVar14;
      cVar2 = '\x01';
      bVar3 = (bool)ExclusiveMonitorPass(piVar14,0x10);
      if (bVar3) {
        *piVar14 = iVar4 + -1;
        cVar2 = ExclusiveMonitorsStatus();
      }
    } while (cVar2 != '\0');
    if (iVar4 < 1) {
      func_0x01006b10();
    }
  }
  return uVar10;
}


