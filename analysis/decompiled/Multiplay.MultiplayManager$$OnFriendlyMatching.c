// name: Multiplay.MultiplayManager$$OnFriendlyMatching
// entry: 013a86f4

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnFriendlyMatching(long param_1,long param_2)

{
  ushort uVar1;
  uint uVar2;
  undefined8 uVar3;
  long lVar4;
  ulong uVar5;
  long *plVar6;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar7;
  long *plVar8;
  undefined8 *puVar9;
  long lVar10;
  int *piVar11;
  undefined8 uVar12;
  
  if ((bRam00000000049f7b0c & 1) == 0) {
    func_0x010c65d4(0x8a55);
    bRam00000000049f7b0c = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_string_TypeInfo);
  }
  uVar12 = **(undefined8 **)(_class_string_TypeInfo + 0xb8);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c();
  }
  lVar4 = Util__GetData(0,uVar3,_str_type,uVar12,0);
  if (lVar4 != 0) {
    if (((*(byte *)(_class_string_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_string_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c();
    }
    uVar5 = System_String__op_Equality(0,lVar4,_str_ChangeMap,0);
    if ((uVar5 & 1) != 0) {
      lVar4 = *(long *)(param_1 + 0x48);
      if (param_2 == 0) {
        func_0x010eec64(0);
      }
      uVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
      if (lVar4 == 0) {
        func_0x010eec64(0);
      }
      BattlePlay_BattlePlayData__DeserializeFriendlyMatching(lVar4,uVar3,0);
    }
  }
  lVar4 = *(long *)(param_1 + 0xb8);
  if (lVar4 == 0) {
    return;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  plVar6 = (long *)SocketIO_SocketIOEvent__get_data(param_2,0);
  if (*(long *)(lVar4 + 0x58) != 0) {
    System_Action<tk2dUIUpDownHoverButton>__Invoke
              (*(long *)(lVar4 + 0x58),plVar6,_mm_Method_System_Action<JsonData>_Invoke__);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x10);
  plVar8 = *(long **)(lVar4 + 0x20);
  puVar9 = *(undefined8 **)(lVar4 + 0x28);
  func_0x010da364(puVar9);
  uVar2 = func_0x01101d6c(puVar9);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar9 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x029a3560. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar8,plVar6,puVar9);
      return;
    }
    plVar8 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar9 + 0x4e) != '\x01') {
      if ((*(short *)((long)puVar9 + 0x4c) != -1) &&
         (((plVar8 == (long *)0x0 || ((*(byte *)(*plVar8 + 0x10d) & 1) == 0)) &&
          (*(long *)(lVar4 + 0x18) != 0)))) {
        uVar5 = func_0x01101d58(puVar9);
        func_0x01101dd4(puVar9);
        uVar7 = func_0x010ba65c();
        if ((uVar5 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar9 + 0x4c);
          if ((uVar7 & 1) == 0) {
            lVar4 = *plVar6 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x130);
            puVar9 = *(undefined8 **)(lVar4 + 0x138);
          }
          else {
            lVar4 = func_0x01101dd4(puVar9);
            lVar10 = *plVar6;
            if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
              uVar5 = 0;
              piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == lVar4) {
                  puVar9 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x029a3740;
                }
                uVar5 = uVar5 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar5 < *(ushort *)(lVar10 + 0x122));
            }
            puVar9 = (undefined8 *)func_0x010bc584(plVar6,lVar4,(ulong)uVar1);
code_r0x029a3740:
            UNRECOVERED_JUMPTABLE = (code *)*puVar9;
            puVar9 = (undefined8 *)puVar9[1];
          }
        }
        else {
          if ((uVar7 & 1) == 0) {
            uVar3 = *(undefined8 *)
                     (*plVar6 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar4 = *plVar6;
            if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
              uVar5 = 0;
              piVar11 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == puVar9[3]) {
                  lVar4 = lVar4 + (ulong)(*piVar11 + (uint)*(ushort *)((long)puVar9 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x029a36f0;
                }
                uVar5 = uVar5 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar5 < *(ushort *)(lVar4 + 0x122));
            }
            lVar4 = func_0x010bc584(plVar6);
code_r0x029a36f0:
            uVar3 = *(undefined8 *)(lVar4 + 8);
          }
          puVar9 = (undefined8 *)func_0x010da294(uVar3,puVar9);
          UNRECOVERED_JUMPTABLE = (code *)*puVar9;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029a35a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(plVar6,puVar9);
      return;
    }
    if (((*(short *)((long)puVar9 + 0x4c) != -1) &&
        ((plVar8 == (long *)0x0 || ((*(byte *)(*plVar8 + 0x10d) & 1) == 0)))) &&
       (*(long *)(lVar4 + 0x18) != 0)) {
      uVar5 = func_0x01101d58(puVar9);
      func_0x01101dd4(puVar9);
      uVar7 = func_0x010ba65c();
      if ((uVar5 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar9 + 0x4c);
        if ((uVar7 & 1) == 0) {
          lVar4 = *plVar8 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar4 + 0x130);
          puVar9 = *(undefined8 **)(lVar4 + 0x138);
        }
        else {
          lVar4 = func_0x01101dd4(puVar9);
          lVar10 = *plVar8;
          if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
            uVar5 = 0;
            piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
            do {
              if (*(long *)(piVar11 + -2) == lVar4) {
                puVar9 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x029a3720;
              }
              uVar5 = uVar5 + 1;
              piVar11 = piVar11 + 4;
            } while (uVar5 < *(ushort *)(lVar10 + 0x122));
          }
          puVar9 = (undefined8 *)func_0x010bc584(plVar8,lVar4,(ulong)uVar1);
code_r0x029a3720:
          UNRECOVERED_JUMPTABLE = (code *)*puVar9;
          puVar9 = (undefined8 *)puVar9[1];
        }
      }
      else {
        if ((uVar7 & 1) == 0) {
          uVar3 = *(undefined8 *)(*plVar8 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar4 = *plVar8;
          if ((ulong)*(ushort *)(lVar4 + 0x122) != 0) {
            uVar5 = 0;
            piVar11 = (int *)(*(long *)(lVar4 + 0xb0) + 8);
            do {
              if (*(long *)(piVar11 + -2) == puVar9[3]) {
                lVar4 = lVar4 + (ulong)(*piVar11 + (uint)*(ushort *)((long)puVar9 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x029a36bc;
              }
              uVar5 = uVar5 + 1;
              piVar11 = piVar11 + 4;
            } while (uVar5 < *(ushort *)(lVar4 + 0x122));
          }
          lVar4 = func_0x010bc584(plVar8);
code_r0x029a36bc:
          uVar3 = *(undefined8 *)(lVar4 + 8);
        }
        puVar9 = (undefined8 *)func_0x010da294(uVar3,puVar9);
        UNRECOVERED_JUMPTABLE = (code *)*puVar9;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x029a3584. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar8,plVar6,puVar9);
  return;
}


