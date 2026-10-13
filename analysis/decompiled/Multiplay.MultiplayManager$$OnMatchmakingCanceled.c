// name: Multiplay.MultiplayManager$$OnMatchmakingCanceled
// entry: 013a7c3c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMatchmakingCanceled(long param_1,long param_2)

{
  ushort uVar1;
  uint uVar2;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar3;
  ulong uVar4;
  long lVar5;
  undefined8 uVar6;
  long lVar7;
  long *plVar8;
  undefined8 uVar9;
  undefined8 *puVar10;
  int *piVar11;
  undefined1 auVar12 [16];
  
  if ((bRam00000000049f7b07 & 1) == 0) {
    func_0x010c65d4(0x8a5d);
    bRam00000000049f7b07 = 1;
  }
  if (((*(byte *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_SceneSingleton<MultiplayIndicator>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = SceneSingleton<WorldBossBattleController>__get_instance
                    (0,_mm_Method_SceneSingleton<MultiplayIndicator>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  MultiplayIndicator__Off(lVar5,0,0);
  if (((*(byte *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Singleton<MultiplayUserStatusPopup>_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Singleton<iTweenManager>__get_instance
                    (0,_mm_Method_Singleton<MultiplayUserStatusPopup>_get_instance__);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  MultiplayUserStatusPopup__Off(lVar5,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  lVar5 = Util__GetData(0,uVar6,_str_uid,0,0);
  lVar7 = MyUserData__get_instance(0,0);
  if (lVar7 == 0) {
    func_0x010eec64(0);
  }
  plVar8 = (long *)UserData__get_info(lVar7,0);
  if (plVar8 == (long *)0x0) {
    func_0x010eec64(0);
  }
  lVar7 = (**(code **)(*plVar8 + 0x1a0))(plVar8,*(undefined8 *)(*plVar8 + 0x1a8));
  if (lVar5 == lVar7) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = LitJson_JsonData__get_Item(lVar7,_str_dodgePaneltyTime,0);
    if (lVar7 != 0) {
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (((*(byte *)(_class_System_DateTime_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_System_DateTime_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c(_class_System_DateTime_TypeInfo);
      }
      auVar12 = System_DateTime__get_Now(0,0);
      if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      auVar12 = Util__GetData(0,uVar6,_str_dodgePaneltyTime,auVar12._0_8_,auVar12._8_8_,0);
      *(undefined1 (*) [16])(param_1 + 0x168) = auVar12;
      PanelCompetitionLobby__CallUpdateDodgePaneltyTime(0,0);
    }
  }
  if (((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) &&
     (lVar7 = BattlePlay_BattlePlayData__get_host(*(long *)(param_1 + 0x48),0), lVar7 != 0)) {
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = BattlePlay_BattlePlayData__get_host(lVar7,0);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    if (*(long *)(lVar7 + 0x10) != lVar5) {
      lVar7 = *(long *)(param_1 + 0x48);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = BattlePlay_BattlePlayData__get_opponent(lVar7,0);
      if (lVar7 != 0) {
        lVar7 = *(long *)(param_1 + 0x48);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        lVar7 = BattlePlay_BattlePlayData__get_opponent(lVar7,0);
        if (lVar7 == 0) {
          func_0x010eec64(0);
        }
        if (*(long *)(lVar7 + 0x10) != lVar5) {
          return;
        }
      }
    }
    lVar7 = *(long *)(param_1 + 0x48);
    if (lVar7 == 0) {
      func_0x010eec64(0);
    }
    lVar7 = BattlePlay_BattlePlayData__get_opponent(lVar7,0);
    if (lVar7 != 0) {
      lVar7 = *(long *)(param_1 + 0x48);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      lVar7 = BattlePlay_BattlePlayData__get_opponent(lVar7,0);
      if (lVar7 == 0) {
        func_0x010eec64(0);
      }
      if (*(long *)(lVar7 + 0x10) == lVar5) {
        puVar10 = (undefined8 *)&str_상대가_매칭을_취소했습니다_;
      }
      else {
        puVar10 = (undefined8 *)&str_매칭이_취소되었습니다_;
      }
      uVar6 = ___L(0,*puVar10,0);
      uVar9 = func_0x01101ad4(_class_object___TypeInfo,0);
      PanelPopupCenterText__Show(0x40400000,0,uVar6,0,uVar9,0);
    }
    lVar5 = *(long *)(param_1 + 0x90);
    *(undefined8 *)(param_1 + 0x40) = 0;
    if (lVar5 != 0) {
      if (*(long *)(lVar5 + 0x58) != 0) {
        System_Action__Invoke(*(long *)(lVar5 + 0x58),0);
      }
      UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar5 + 0x10);
      plVar8 = *(long **)(lVar5 + 0x20);
      puVar10 = *(undefined8 **)(lVar5 + 0x28);
      func_0x010da364(puVar10);
      uVar2 = func_0x01101d6c(puVar10);
      if ((uVar2 & 1) == 0) {
        if (*(char *)((long)puVar10 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE_00)(0,plVar8,puVar10);
          return;
        }
        plVar8 = (long *)0x0;
      }
      else if (((*(short *)((long)puVar10 + 0x4c) != -1) &&
               ((plVar8 == (long *)0x0 || ((*(byte *)(*plVar8 + 0x10d) & 1) == 0)))) &&
              (*(long *)(lVar5 + 0x18) != 0)) {
        uVar3 = func_0x01101d58(puVar10);
        func_0x01101dd4(puVar10);
        uVar4 = func_0x010ba65c();
        if ((uVar3 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar10 + 0x4c);
          if ((uVar4 & 1) == 0) {
            lVar5 = *plVar8 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar5 + 0x130);
            puVar10 = *(undefined8 **)(lVar5 + 0x138);
          }
          else {
            lVar5 = func_0x01101dd4(puVar10);
            lVar7 = *plVar8;
            if ((ulong)*(ushort *)(lVar7 + 0x122) != 0) {
              uVar3 = 0;
              piVar11 = (int *)(*(long *)(lVar7 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == lVar5) {
                  puVar10 = (undefined8 *)(lVar7 + (ulong)(*piVar11 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x0109246c;
                }
                uVar3 = uVar3 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar3 < *(ushort *)(lVar7 + 0x122));
            }
            puVar10 = (undefined8 *)func_0x010bc584(plVar8,lVar5,(ulong)uVar1);
code_r0x0109246c:
            UNRECOVERED_JUMPTABLE_00 = (code *)*puVar10;
            puVar10 = (undefined8 *)puVar10[1];
          }
        }
        else {
          if ((uVar4 & 1) == 0) {
            uVar6 = *(undefined8 *)
                     (*plVar8 + (ulong)*(ushort *)((long)puVar10 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar5 = *plVar8;
            if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
              uVar3 = 0;
              piVar11 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == puVar10[3]) {
                  lVar5 = lVar5 + (ulong)(*piVar11 + (uint)*(ushort *)((long)puVar10 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x0109243c;
                }
                uVar3 = uVar3 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar3 < *(ushort *)(lVar5 + 0x122));
            }
            lVar5 = func_0x010bc584(plVar8);
code_r0x0109243c:
            uVar6 = *(undefined8 *)(lVar5 + 8);
          }
          puVar10 = (undefined8 *)func_0x010da294(uVar6,puVar10);
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar10;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(plVar8,puVar10);
      return;
    }
  }
  return;
}


