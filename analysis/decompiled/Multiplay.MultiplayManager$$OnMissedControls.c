// name: Multiplay.MultiplayManager$$OnMissedControls
// entry: 013a944c

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnMissedControls(long param_1,long param_2)

{
  uint uVar1;
  int iVar2;
  undefined4 uVar3;
  int iVar4;
  undefined8 uVar5;
  long lVar6;
  long *plVar7;
  undefined8 uVar8;
  long lVar9;
  long lVar10;
  code *pcVar11;
  ulong uVar12;
  long lVar13;
  long *plVar14;
  
  if ((bRam00000000049f7b11 & 1) == 0) {
    func_0x010c65d4(0x8a5e);
    bRam00000000049f7b11 = 1;
  }
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar1 = Multiplay_MultiplayManager__get_isContinuedBattle();
    if ((uVar1 & 1) == 0) {
      if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar1 = Multiplay_MultiplayManager__get_isLoadingBattleForWatching();
      if ((uVar1 & 1) == 0) {
        Multiplay_MultiplayManager__CheckMultiplayState(param_1);
      }
    }
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    lVar6 = LitJson_JsonData__get_Item(lVar6,_str_controls,0);
    if ((lVar6 != 0) && (iVar2 = LitJson_JsonData__get_Count(lVar6,0), 0 < iVar2)) {
      uVar3 = LitJson_JsonData__get_Count(lVar6,0);
      plVar7 = (long *)func_0x01101ad4(_class_BattlePlay_BattleControl___TypeInfo,uVar3);
      iVar2 = LitJson_JsonData__get_Count(lVar6,0);
      if (0 < iVar2) {
        uVar12 = 0;
        do {
          uVar8 = LitJson_JsonData__get_Item(lVar6,uVar12 & 0xffffffff,0);
          if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
             (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
            func_0x010da55c(_class_Util_TypeInfo);
          }
          iVar4 = Util__GetData(0,uVar8,_str_type,0,0);
          uVar8 = LitJson_JsonData__get_Item(lVar6,uVar12 & 0xffffffff,0);
          if (iVar4 == 0xb) {
            lVar9 = func_0x010fdda8(_class_BattlePlay_UserSpellControl_TypeInfo);
            BattlePlay_UserSpellControl___ctor(lVar9,uVar8,0);
          }
          else {
            lVar9 = func_0x010fdda8(_class_BattlePlay_BattleControl_TypeInfo);
            BattlePlay_BattleControl___ctor(lVar9,uVar8,0);
          }
          if (plVar7 == (long *)0x0) {
            func_0x010eec64(0);
          }
          if ((lVar9 != 0) &&
             (lVar10 = func_0x010fdfcc(lVar9,*(undefined8 *)(*plVar7 + 0x40)), lVar10 == 0)) {
            uVar8 = func_0x010efe90();
            func_0x010eebec(uVar8,0,0);
          }
          if (*(uint *)(plVar7 + 3) <= uVar12) {
            uVar8 = func_0x010ef850();
            func_0x010eebec(uVar8,0,0);
          }
          plVar7[uVar12 + 4] = lVar9;
          uVar12 = uVar12 + 1;
        } while (iVar2 != (int)uVar12);
      }
      if (((*(byte *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_Singleton<BattlePlayManager>_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      lVar6 = Singleton<iTweenManager>__get_instance
                        (0,_mm_Method_Singleton<BattlePlayManager>_get_instance__);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam00000000049fae0a & 1) == 0) {
        func_0x010c65d4(0x205c,plVar7,0);
        bRam00000000049fae0a = 1;
      }
      if (plVar7 == (long *)0x0) {
        func_0x010eec64(0);
      }
      lVar9 = plVar7[3];
      uVar1 = 0;
      while( true ) {
        lVar10 = *(long *)(lVar6 + 0x18);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        iVar2 = BattlePlay_BattlePlayData__get_controlIdx(lVar10);
        if ((int)lVar9 <= (int)uVar1) break;
        if (plVar7 == (long *)0x0) {
          func_0x010eec64(0);
        }
        if (*(uint *)(plVar7 + 3) <= uVar1) {
          uVar8 = func_0x010ef850();
          func_0x010eebec(uVar8,0,0);
        }
        plVar14 = plVar7 + (long)(int)uVar1 + 4;
        lVar10 = *plVar14;
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        if (iVar2 <= *(int *)(lVar10 + 0x1c)) {
          lVar10 = *(long *)(lVar6 + 0x20);
          if (plVar7 == (long *)0x0) {
            func_0x010eec64(0);
          }
          if (*(uint *)(plVar7 + 3) <= uVar1) {
            uVar8 = func_0x010ef850();
            func_0x010eebec(uVar8,0,0);
          }
          lVar13 = *plVar14;
          if (lVar13 == 0) {
            func_0x010eec64(0);
          }
          uVar3 = *(undefined4 *)(lVar13 + 0x1c);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          uVar12 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                             (lVar10,uVar3,
                              _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                             );
          if ((uVar12 & 1) == 0) {
            lVar10 = *(long *)(lVar6 + 0x20);
            if (plVar7 == (long *)0x0) {
              func_0x010eec64(0);
            }
            if (*(uint *)(plVar7 + 3) <= uVar1) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            lVar13 = *plVar14;
            if (lVar13 == 0) {
              func_0x010eec64(0);
            }
            uVar3 = *(undefined4 *)(lVar13 + 0x1c);
            if (*(uint *)(plVar7 + 3) <= uVar1) {
              uVar8 = func_0x010ef850();
              func_0x010eebec(uVar8,0,0);
            }
            lVar13 = *plVar14;
            if (lVar10 == 0) {
              func_0x010eec64(0);
            }
            System_Collections_Generic_Dictionary<int,_UserUnitRelation>__Add
                      (lVar10,uVar3,lVar13,
                       _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Add__);
          }
        }
        uVar1 = uVar1 + 1;
      }
      while( true ) {
        if ((bRam00000000049fae0f & 1) == 0) {
          func_0x010c65d4(0x2060);
          bRam00000000049fae0f = 1;
        }
        lVar9 = *(long *)(lVar6 + 0x20);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar12 = System_Collections_Generic_Dictionary<int,_tk2dFontChar>__ContainsKey
                           (lVar9,iVar2,
                            _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_ContainsKey__
                           );
        if ((uVar12 & 1) == 0) break;
        lVar9 = *(long *)(lVar6 + 0x20);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        lVar9 = System_Collections_Generic_Dictionary<int,_object>__get_Item
                          (lVar9,iVar2,
                           _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_get_Item__
                          );
        lVar10 = *(long *)(lVar6 + 0x20);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        uVar3 = *(undefined4 *)(lVar9 + 0x1c);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        System_Collections_Generic_Dictionary<int,_EndPointListener>__Remove
                  (lVar10,uVar3,
                   _mm_Method_System_Collections_Generic_Dictionary<int,_BattleControl>_Remove__);
        iVar2 = *(int *)(lVar9 + 0x1c);
        lVar10 = *(long *)(lVar6 + 0x18);
        if (lVar10 == 0) {
          func_0x010eec64(0);
        }
        iVar4 = BattlePlay_BattlePlayData__get_controlIdx(lVar10);
        if (iVar2 == iVar4) {
          lVar10 = *(long *)(lVar6 + 0x18);
          if (lVar10 == 0) {
            func_0x010eec64(0);
          }
          BattlePlay_BattlePlayData__AddControl(lVar10,lVar9);
        }
        lVar9 = *(long *)(lVar6 + 0x18);
        if (lVar9 == 0) {
          func_0x010eec64(0);
        }
        iVar2 = BattlePlay_BattlePlayData__get_controlIdx(lVar9);
      }
      return;
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
  uVar1 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar1 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar1 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar1 & 1) != 0) {
    return;
  }
  plVar7 = *(long **)(param_1 + 0x40);
  if (plVar7 == (long *)0x0) {
code_r0x013a22a0:
    uVar3 = 8;
  }
  else {
    iVar2 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
    if (0 < iVar2) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar6 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar6 != 0))
      {
        lVar6 = *(long *)(param_1 + 0x48);
        if (lVar6 == 0) {
          func_0x010eec64(0);
        }
        uVar8 = BattlePlay_BattlePlayData__get_battleKey(lVar6,0);
        plVar7 = *(long **)(param_1 + 0x40);
        if (plVar7 == (long *)0x0) {
          uVar3 = 8;
        }
        else {
          uVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
        }
        uVar5 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar5,uVar8,1,0,uVar3,_mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__
                  );
        goto code_r0x013a22d4;
      }
    }
    plVar7 = *(long **)(param_1 + 0x40);
    if (plVar7 == (long *)0x0) goto code_r0x013a22a0;
    uVar3 = (**(code **)(*plVar7 + 0x170))(plVar7,*(undefined8 *)(*plVar7 + 0x178));
  }
  uVar5 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar5,uVar3,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar5 = LitJson_JsonMapper__ToJson(0,uVar5,0);
  uVar8 = _str_cancelMatchmaking;
  uVar12 = MyUserData__get_hasInstance(0,0);
  if ((uVar12 & 1) != 0) {
    lVar6 = MyUserData__get_instance(0,0);
    if (lVar6 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar6 + 0x268) != '\0') {
      lVar6 = *(long *)(param_1 + 0x30);
      if (lVar6 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar8,uVar5,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar8 = System_String__Format(0,_str____0____1__,uVar8,uVar5,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar5 = LitJson_JsonMapper__ToObject(0,uVar8,0);
      lVar10 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar8 = _str__;
      System_Object___ctor(lVar10,0);
      *(undefined4 *)(lVar10 + 0x14) = 2;
      *(undefined4 *)(lVar10 + 0x18) = 0;
      *(undefined8 *)(lVar10 + 0x30) = uVar5;
      *(undefined8 *)(lVar10 + 0x20) = uVar8;
      *(undefined4 *)(lVar10 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar10 + 0x10) = 4;
      lVar9 = lVar6;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar9 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar13 = *(long *)(lVar6 + 0x48);
      if (*(long *)(lVar6 + 0x50) == 0) {
        lVar9 = func_0x010eec64(0);
      }
      uVar8 = SocketIO_Encoder__Encode(lVar9,lVar10);
      if (lVar13 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar13,uVar8,0);
      return;
    }
  }
  uVar12 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar12 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar8 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar12 = UnityEngine_Object__op_Inequality(0,uVar8,0,0);
  if ((uVar12 & 1) == 0) {
    return;
  }
  lVar6 = *(long *)(param_1 + 0x30);
  if (lVar6 == 0) {
    func_0x010eec64(0);
  }
  SocketIO_SocketIOComponent__Close(lVar6,0);
  uVar8 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  if ((bRam0000000004a04120 & 1) == 0) {
    func_0x010c65d4(0x906e,uVar8,0);
    bRam0000000004a04120 = 1;
  }
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  pcVar11 = pcRam0000000004a04118;
  if ((pcRam0000000004a04118 == (code *)0x0) &&
     (pcVar11 = (code *)func_0x0111e49c(&UNK_039a20c7), pcVar11 == (code *)0x0)) {
    uVar5 = func_0x010f000c(&UNK_039a20c7);
    func_0x010eebec(uVar5,0,0);
  }
  pcRam0000000004a04118 = pcVar11;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*pcRam0000000004a04118)(uVar8,0);
  return;
}


