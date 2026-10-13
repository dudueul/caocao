// name: Multiplay.MultiplayManager$$OnReadySet
// entry: 013a8e90

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnReadySet(long param_1,long param_2)

{
  long lVar1;
  ushort uVar2;
  uint uVar3;
  int iVar4;
  undefined4 uVar5;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar6;
  ulong uVar7;
  long *plVar8;
  undefined8 *puVar9;
  undefined8 uVar10;
  undefined8 uVar11;
  long lVar12;
  long lVar13;
  long lVar14;
  int *piVar15;
  
  *(undefined1 *)(param_1 + 0x142) = 0;
  if ((*(long *)(param_1 + 0x40) != 0) && (*(long *)(param_1 + 0x48) != 0)) {
    Multiplay_MultiplayManager__CheckMultiplayState(param_1);
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar11 = SocketIO_SocketIOEvent__get_data(param_2,0);
    Multiplay_Ready__Deserialize(0,uVar11,0);
    lVar12 = *(long *)(param_1 + 0xe0);
    if (lVar12 == 0) {
      return;
    }
    if (*(long *)(lVar12 + 0x58) != 0) {
      System_Action__Invoke(*(long *)(lVar12 + 0x58),0);
    }
    UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar12 + 0x10);
    plVar8 = *(long **)(lVar12 + 0x20);
    puVar9 = *(undefined8 **)(lVar12 + 0x28);
    func_0x010da364(puVar9);
    uVar3 = func_0x01101d6c(puVar9);
    if ((uVar3 & 1) == 0) {
      if (*(char *)((long)puVar9 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
        (*UNRECOVERED_JUMPTABLE_00)(0,plVar8,puVar9);
        return;
      }
      plVar8 = (long *)0x0;
    }
    else if ((*(short *)((long)puVar9 + 0x4c) != -1) &&
            (((plVar8 == (long *)0x0 || ((*(byte *)(*plVar8 + 0x10d) & 1) == 0)) &&
             (*(long *)(lVar12 + 0x18) != 0)))) {
      uVar6 = func_0x01101d58(puVar9);
      func_0x01101dd4(puVar9);
      uVar7 = func_0x010ba65c();
      if ((uVar6 & 1) == 0) {
        uVar2 = *(ushort *)((long)puVar9 + 0x4c);
        if ((uVar7 & 1) == 0) {
          lVar12 = *plVar8 + (ulong)uVar2 * 0x10;
          UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar12 + 0x130);
          puVar9 = *(undefined8 **)(lVar12 + 0x138);
        }
        else {
          lVar12 = func_0x01101dd4(puVar9);
          lVar14 = *plVar8;
          if ((ulong)*(ushort *)(lVar14 + 0x122) != 0) {
            uVar6 = 0;
            piVar15 = (int *)(*(long *)(lVar14 + 0xb0) + 8);
            do {
              if (*(long *)(piVar15 + -2) == lVar12) {
                puVar9 = (undefined8 *)(lVar14 + (ulong)(*piVar15 + (uint)uVar2) * 0x10 + 0x130);
                goto code_r0x0109246c;
              }
              uVar6 = uVar6 + 1;
              piVar15 = piVar15 + 4;
            } while (uVar6 < *(ushort *)(lVar14 + 0x122));
          }
          puVar9 = (undefined8 *)func_0x010bc584(plVar8,lVar12,(ulong)uVar2);
code_r0x0109246c:
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar9;
          puVar9 = (undefined8 *)puVar9[1];
        }
      }
      else {
        if ((uVar7 & 1) == 0) {
          uVar11 = *(undefined8 *)(*plVar8 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138)
          ;
        }
        else {
          lVar12 = *plVar8;
          if ((ulong)*(ushort *)(lVar12 + 0x122) != 0) {
            uVar6 = 0;
            piVar15 = (int *)(*(long *)(lVar12 + 0xb0) + 8);
            do {
              if (*(long *)(piVar15 + -2) == puVar9[3]) {
                lVar12 = lVar12 + (ulong)(*piVar15 + (uint)*(ushort *)((long)puVar9 + 0x4c)) * 0x10
                         + 0x130;
                goto code_r0x0109243c;
              }
              uVar6 = uVar6 + 1;
              piVar15 = piVar15 + 4;
            } while (uVar6 < *(ushort *)(lVar12 + 0x122));
          }
          lVar12 = func_0x010bc584(plVar8);
code_r0x0109243c:
          uVar11 = *(undefined8 *)(lVar12 + 8);
        }
        puVar9 = (undefined8 *)func_0x010da294(uVar11,puVar9);
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar9;
      }
    }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*UNRECOVERED_JUMPTABLE_00)(plVar8,puVar9);
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
  uVar3 = Multiplay_MultiplayManager__get_isContinuedBattle();
  if ((uVar3 & 1) != 0) {
    return;
  }
  if (((*(byte *)(_class_Multiplay_MultiplayManager_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Multiplay_MultiplayManager_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar3 = Multiplay_MultiplayManager__get_isWatchingBattle();
  if ((uVar3 & 1) != 0) {
    return;
  }
  plVar8 = *(long **)(param_1 + 0x40);
  if (plVar8 == (long *)0x0) {
code_r0x013a22a0:
    uVar5 = 8;
  }
  else {
    iVar4 = (**(code **)(*plVar8 + 0x170))(plVar8,*(undefined8 *)(*plVar8 + 0x178));
    if (0 < iVar4) {
      if (*(long *)(param_1 + 0x40) == 0) goto code_r0x013a22a0;
      if ((*(long *)(param_1 + 0x48) != 0) &&
         (lVar12 = BattlePlay_BattlePlayData__get_opponent(*(long *)(param_1 + 0x48),0), lVar12 != 0
         )) {
        lVar12 = *(long *)(param_1 + 0x48);
        if (lVar12 == 0) {
          func_0x010eec64(0);
        }
        uVar11 = BattlePlay_BattlePlayData__get_battleKey(lVar12,0);
        plVar8 = *(long **)(param_1 + 0x40);
        if (plVar8 == (long *)0x0) {
          uVar5 = 8;
        }
        else {
          uVar5 = (**(code **)(*plVar8 + 0x170))(plVar8,*(undefined8 *)(*plVar8 + 0x178));
        }
        uVar10 = func_0x010fdda8(_class_<>__AnonType18<string,_bool,_bool,_Step>_TypeInfo);
        <>__AnonType18<string,_bool,_bool,_Step>___ctor
                  (uVar10,uVar11,1,0,uVar5,
                   _mm_Method_<>__AnonType18<string,_bool,_bool,_Step>__ctor__);
        goto code_r0x013a22d4;
      }
    }
    plVar8 = *(long **)(param_1 + 0x40);
    if (plVar8 == (long *)0x0) goto code_r0x013a22a0;
    uVar5 = (**(code **)(*plVar8 + 0x170))(plVar8,*(undefined8 *)(*plVar8 + 0x178));
  }
  uVar10 = func_0x010fdda8(_class_<>__AnonType19<Step,_bool,_bool>_TypeInfo);
  <>__AnonType19<Step,_bool,_bool>___ctor
            (uVar10,uVar5,1,0,_mm_Method_<>__AnonType19<Step,_bool,_bool>__ctor__);
code_r0x013a22d4:
  if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar10 = LitJson_JsonMapper__ToJson(0,uVar10,0);
  uVar11 = _str_cancelMatchmaking;
  uVar6 = MyUserData__get_hasInstance(0,0);
  if ((uVar6 & 1) != 0) {
    lVar12 = MyUserData__get_instance(0,0);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    if (*(char *)(lVar12 + 0x268) != '\0') {
      lVar12 = *(long *)(param_1 + 0x30);
      if (lVar12 == 0) {
        func_0x010eec64(0);
      }
      if ((bRam0000000004a00249 & 1) == 0) {
        func_0x010c65d4(0xc5d1,uVar11,uVar10,0);
        bRam0000000004a00249 = 1;
      }
      if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar11 = System_String__Format(0,_str____0____1__,uVar11,uVar10,0);
      if ((bRam0000000004a0024d & 1) == 0) {
        func_0x010c65d4(0xc5ce);
        bRam0000000004a0024d = 1;
      }
      if (((*(byte *)(_class_LitJson_JsonMapper_TypeInfo + 0x12a) & 1) != 0) &&
         (*(int *)(_class_LitJson_JsonMapper_TypeInfo + 0xd8) == 0)) {
        func_0x010da55c();
      }
      uVar10 = LitJson_JsonMapper__ToObject(0,uVar11,0);
      lVar13 = func_0x010fdda8(_class_SocketIO_Packet_TypeInfo);
      uVar11 = _str__;
      System_Object___ctor(lVar13,0);
      *(undefined4 *)(lVar13 + 0x14) = 2;
      *(undefined4 *)(lVar13 + 0x18) = 0;
      *(undefined8 *)(lVar13 + 0x30) = uVar10;
      *(undefined8 *)(lVar13 + 0x20) = uVar11;
      *(undefined4 *)(lVar13 + 0x28) = 0xffffffff;
      *(undefined4 *)(lVar13 + 0x10) = 4;
      lVar14 = lVar12;
      if ((bRam0000000004a0024f & 1) == 0) {
        lVar14 = func_0x010c65d4(0xc5cf);
        bRam0000000004a0024f = 1;
      }
      lVar1 = *(long *)(lVar12 + 0x48);
      if (*(long *)(lVar12 + 0x50) == 0) {
        lVar14 = func_0x010eec64(0);
      }
      uVar11 = SocketIO_Encoder__Encode(lVar14,lVar13);
      if (lVar1 == 0) {
        func_0x010eec64(0);
      }
      WebSocketSharp_WebSocket__Send(lVar1,uVar11,0);
      return;
    }
  }
  uVar6 = Multiplay_MultiplayManager__get_isConnected(param_1);
  if ((uVar6 & 1) == 0) {
    return;
  }
  if ((bRam00000000049f7acc & 1) == 0) {
    func_0x010c65d4(0x8a26);
    bRam00000000049f7acc = 1;
  }
  *(undefined8 *)(param_1 + 0x70) = 0;
  *(undefined1 *)(param_1 + 0x141) = 0;
  *(undefined1 *)(param_1 + 0x142) = 0;
  uVar11 = *(undefined8 *)(param_1 + 0x30);
  if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  uVar6 = UnityEngine_Object__op_Inequality(0,uVar11,0,0);
  if ((uVar6 & 1) != 0) {
    lVar12 = *(long *)(param_1 + 0x30);
    if (lVar12 == 0) {
      func_0x010eec64(0);
    }
    SocketIO_SocketIOComponent__Close(lVar12,0);
    uVar11 = *(undefined8 *)(param_1 + 0x30);
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    if ((bRam0000000004a04120 & 1) == 0) {
      func_0x010c65d4(0x906e,uVar11,0);
      bRam0000000004a04120 = 1;
    }
    if (((*(byte *)(_class_UnityEngine_Object_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_UnityEngine_Object_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    UNRECOVERED_JUMPTABLE_00 = pcRam0000000004a04118;
    if ((pcRam0000000004a04118 == (code *)0x0) &&
       (UNRECOVERED_JUMPTABLE_00 = (code *)func_0x0111e49c(&UNK_039a20c7),
       UNRECOVERED_JUMPTABLE_00 == (code *)0x0)) {
      uVar10 = func_0x010f000c(&UNK_039a20c7);
      func_0x010eebec(uVar10,0,0);
    }
    pcRam0000000004a04118 = UNRECOVERED_JUMPTABLE_00;
                    /* WARNING: Could not recover jumptable at 0x02ac070c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
    (*pcRam0000000004a04118)(uVar11,0);
    return;
  }
  return;
}


