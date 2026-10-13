// name: Multiplay.MultiplayManager$$OnFriendlyMatchingRoomUpdated
// entry: 013a8214

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnFriendlyMatchingRoomUpdated(long param_1,long param_2)

{
  ushort uVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  long lVar5;
  ulong uVar6;
  long *plVar7;
  long lVar8;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar9;
  long *plVar10;
  undefined8 *puVar11;
  int *piVar12;
  undefined8 uVar13;
  undefined8 uVar14;
  
  if ((bRam00000000049f7b0a & 1) == 0) {
    func_0x010c65d4(0x8a54);
    bRam00000000049f7b0a = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  lVar3 = LitJson_JsonData__get_Item(lVar3,_str_roomInfo,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar13 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (lVar3 != 0) {
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar13 = Util__GetData(0,lVar3,_str_roomId,uVar13,0);
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar14 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar5 = Util__GetData(0,uVar4,_str_newHostName,uVar14,0);
  uVar6 = System_String__op_Inequality(0,lVar5,**(undefined8 **)(_class_string_TypeInfo + 0xb8),0);
  if ((uVar6 & 1) != 0) {
    uVar4 = ___L(0,
                 _str_방장의_연결이_끊겼습니다___0__국가가_방장이_되었습니다_,
                 0);
    plVar7 = (long *)func_0x01101ad4(_class_object___TypeInfo,1);
    if (plVar7 == (long *)0x0) {
      func_0x010eec64(0);
    }
    if ((lVar5 != 0) && (lVar8 = func_0x010fdfcc(lVar5,*(undefined8 *)(*plVar7 + 0x40)), lVar8 == 0)
       ) {
      uVar14 = func_0x010efe90();
      func_0x010eebec(uVar14,0,0);
    }
    if ((int)plVar7[3] == 0) {
      uVar14 = func_0x010ef850();
      func_0x010eebec(uVar14,0,0);
    }
    plVar7[4] = lVar5;
    PanelPopupCenterText__Show(0x40000000,0,uVar4,0,plVar7,0);
  }
  lVar5 = CompetitionManager__get_instance(0,0);
  if (lVar5 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = CompetitionManager__get_friendlyMatchingRoomId(lVar5,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar6 = System_String__op_Inequality(0,uVar4,**(undefined8 **)(_class_string_TypeInfo + 0xb8),0);
  if ((uVar6 & 1) != 0) {
    lVar5 = CompetitionManager__get_instance(0,0);
    if (lVar5 == 0) {
      func_0x010eec64(0);
    }
    uVar4 = CompetitionManager__get_friendlyMatchingRoomId(lVar5,0);
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_string_TypeInfo);
    }
    uVar6 = System_String__op_Equality(0,uVar4,uVar13,0);
    if ((uVar6 & 1) != 0) {
      lVar5 = CompetitionManager__get_instance(0,0);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      lVar5 = *(long *)(lVar5 + 0xd8);
      if (lVar5 == 0) {
        func_0x010eec64(0);
      }
      FriendlyMatchingRoom__Deserialize(lVar5,lVar3,0);
      lVar3 = CompetitionManager__get_instance(0,0);
      if (lVar3 == 0) {
        func_0x010eec64(0);
      }
      plVar7 = *(long **)(lVar3 + 0xd8);
      goto code_r0x013a85a8;
    }
  }
  plVar7 = (long *)func_0x010fdda8(_class_FriendlyMatchingRoom_TypeInfo);
  FriendlyMatchingRoom___ctor(plVar7,lVar3,0);
code_r0x013a85a8:
  lVar3 = *(long *)(param_1 + 0xa8);
  if (lVar3 == 0) {
    return;
  }
  if (*(long *)(lVar3 + 0x58) != 0) {
    System_Action<tk2dUIUpDownHoverButton>__Invoke
              (*(long *)(lVar3 + 0x58),plVar7,
               _mm_Method_System_Action<FriendlyMatchingRoom>_Invoke__);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x10);
  plVar10 = *(long **)(lVar3 + 0x20);
  puVar11 = *(undefined8 **)(lVar3 + 0x28);
  func_0x010da364(puVar11);
  uVar2 = func_0x01101d6c(puVar11);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar11 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x029a3560. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar10,plVar7,puVar11);
      return;
    }
    plVar10 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar11 + 0x4e) != '\x01') {
      if ((*(short *)((long)puVar11 + 0x4c) != -1) &&
         (((plVar10 == (long *)0x0 || ((*(byte *)(*plVar10 + 0x10d) & 1) == 0)) &&
          (*(long *)(lVar3 + 0x18) != 0)))) {
        uVar6 = func_0x01101d58(puVar11);
        func_0x01101dd4(puVar11);
        uVar9 = func_0x010ba65c();
        if ((uVar6 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar11 + 0x4c);
          if ((uVar9 & 1) == 0) {
            lVar3 = *plVar7 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
            puVar11 = *(undefined8 **)(lVar3 + 0x138);
          }
          else {
            lVar3 = func_0x01101dd4(puVar11);
            lVar5 = *plVar7;
            if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
              uVar6 = 0;
              piVar12 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
              do {
                if (*(long *)(piVar12 + -2) == lVar3) {
                  puVar11 = (undefined8 *)(lVar5 + (ulong)(*piVar12 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x029a3740;
                }
                uVar6 = uVar6 + 1;
                piVar12 = piVar12 + 4;
              } while (uVar6 < *(ushort *)(lVar5 + 0x122));
            }
            puVar11 = (undefined8 *)func_0x010bc584(plVar7,lVar3,(ulong)uVar1);
code_r0x029a3740:
            UNRECOVERED_JUMPTABLE = (code *)*puVar11;
            puVar11 = (undefined8 *)puVar11[1];
          }
        }
        else {
          if ((uVar9 & 1) == 0) {
            uVar13 = *(undefined8 *)
                      (*plVar7 + (ulong)*(ushort *)((long)puVar11 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar3 = *plVar7;
            if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
              uVar6 = 0;
              piVar12 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
              do {
                if (*(long *)(piVar12 + -2) == puVar11[3]) {
                  lVar3 = lVar3 + (ulong)(*piVar12 + (uint)*(ushort *)((long)puVar11 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x029a36f0;
                }
                uVar6 = uVar6 + 1;
                piVar12 = piVar12 + 4;
              } while (uVar6 < *(ushort *)(lVar3 + 0x122));
            }
            lVar3 = func_0x010bc584(plVar7);
code_r0x029a36f0:
            uVar13 = *(undefined8 *)(lVar3 + 8);
          }
          puVar11 = (undefined8 *)func_0x010da294(uVar13,puVar11);
          UNRECOVERED_JUMPTABLE = (code *)*puVar11;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029a35a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(plVar7,puVar11);
      return;
    }
    if (((*(short *)((long)puVar11 + 0x4c) != -1) &&
        ((plVar10 == (long *)0x0 || ((*(byte *)(*plVar10 + 0x10d) & 1) == 0)))) &&
       (*(long *)(lVar3 + 0x18) != 0)) {
      uVar6 = func_0x01101d58(puVar11);
      func_0x01101dd4(puVar11);
      uVar9 = func_0x010ba65c();
      if ((uVar6 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar11 + 0x4c);
        if ((uVar9 & 1) == 0) {
          lVar3 = *plVar10 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
          puVar11 = *(undefined8 **)(lVar3 + 0x138);
        }
        else {
          lVar3 = func_0x01101dd4(puVar11);
          lVar5 = *plVar10;
          if ((ulong)*(ushort *)(lVar5 + 0x122) != 0) {
            uVar6 = 0;
            piVar12 = (int *)(*(long *)(lVar5 + 0xb0) + 8);
            do {
              if (*(long *)(piVar12 + -2) == lVar3) {
                puVar11 = (undefined8 *)(lVar5 + (ulong)(*piVar12 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x029a3720;
              }
              uVar6 = uVar6 + 1;
              piVar12 = piVar12 + 4;
            } while (uVar6 < *(ushort *)(lVar5 + 0x122));
          }
          puVar11 = (undefined8 *)func_0x010bc584(plVar10,lVar3,(ulong)uVar1);
code_r0x029a3720:
          UNRECOVERED_JUMPTABLE = (code *)*puVar11;
          puVar11 = (undefined8 *)puVar11[1];
        }
      }
      else {
        if ((uVar9 & 1) == 0) {
          uVar13 = *(undefined8 *)
                    (*plVar10 + (ulong)*(ushort *)((long)puVar11 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar3 = *plVar10;
          if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
            uVar6 = 0;
            piVar12 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
            do {
              if (*(long *)(piVar12 + -2) == puVar11[3]) {
                lVar3 = lVar3 + (ulong)(*piVar12 + (uint)*(ushort *)((long)puVar11 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x029a36bc;
              }
              uVar6 = uVar6 + 1;
              piVar12 = piVar12 + 4;
            } while (uVar6 < *(ushort *)(lVar3 + 0x122));
          }
          lVar3 = func_0x010bc584(plVar10);
code_r0x029a36bc:
          uVar13 = *(undefined8 *)(lVar3 + 8);
        }
        puVar11 = (undefined8 *)func_0x010da294(uVar13,puVar11);
        UNRECOVERED_JUMPTABLE = (code *)*puVar11;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x029a3584. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar10,plVar7,puVar11);
  return;
}


