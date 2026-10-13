// name: Multiplay.MultiplayManager$$OnFriendlyMatchingRoomCreated
// entry: 013a8138

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnFriendlyMatchingRoomCreated(long param_1,long param_2)

{
  ushort uVar1;
  uint uVar2;
  long lVar3;
  undefined8 uVar4;
  long *plVar5;
  code *UNRECOVERED_JUMPTABLE;
  ulong uVar6;
  ulong uVar7;
  long *plVar8;
  undefined8 *puVar9;
  long lVar10;
  int *piVar11;
  
  if ((bRam00000000049f7b09 & 1) == 0) {
    func_0x010c65d4(0x8a53);
    bRam00000000049f7b09 = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  lVar3 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (lVar3 == 0) {
    func_0x010eec64(0);
  }
  uVar4 = LitJson_JsonData__get_Item(lVar3,_str_roomInfo,0);
  plVar5 = (long *)func_0x010fdda8(_class_FriendlyMatchingRoom_TypeInfo);
  FriendlyMatchingRoom___ctor(plVar5,uVar4,0);
  lVar3 = *(long *)(param_1 + 0xa0);
  if (lVar3 == 0) {
    return;
  }
  if (*(long *)(lVar3 + 0x58) != 0) {
    System_Action<tk2dUIUpDownHoverButton>__Invoke
              (*(long *)(lVar3 + 0x58),plVar5,
               _mm_Method_System_Action<FriendlyMatchingRoom>_Invoke__);
  }
  UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x10);
  plVar8 = *(long **)(lVar3 + 0x20);
  puVar9 = *(undefined8 **)(lVar3 + 0x28);
  func_0x010da364(puVar9);
  uVar2 = func_0x01101d6c(puVar9);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar9 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x029a3560. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(0,plVar8,plVar5,puVar9);
      return;
    }
    plVar8 = (long *)0x0;
  }
  else {
    if (*(char *)((long)puVar9 + 0x4e) != '\x01') {
      if ((*(short *)((long)puVar9 + 0x4c) != -1) &&
         (((plVar8 == (long *)0x0 || ((*(byte *)(*plVar8 + 0x10d) & 1) == 0)) &&
          (*(long *)(lVar3 + 0x18) != 0)))) {
        uVar6 = func_0x01101d58(puVar9);
        func_0x01101dd4(puVar9);
        uVar7 = func_0x010ba65c();
        if ((uVar6 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar9 + 0x4c);
          if ((uVar7 & 1) == 0) {
            lVar3 = *plVar5 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
            puVar9 = *(undefined8 **)(lVar3 + 0x138);
          }
          else {
            lVar3 = func_0x01101dd4(puVar9);
            lVar10 = *plVar5;
            if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
              uVar6 = 0;
              piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == lVar3) {
                  puVar9 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x029a3740;
                }
                uVar6 = uVar6 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar6 < *(ushort *)(lVar10 + 0x122));
            }
            puVar9 = (undefined8 *)func_0x010bc584(plVar5,lVar3,(ulong)uVar1);
code_r0x029a3740:
            UNRECOVERED_JUMPTABLE = (code *)*puVar9;
            puVar9 = (undefined8 *)puVar9[1];
          }
        }
        else {
          if ((uVar7 & 1) == 0) {
            uVar4 = *(undefined8 *)
                     (*plVar5 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar3 = *plVar5;
            if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
              uVar6 = 0;
              piVar11 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
              do {
                if (*(long *)(piVar11 + -2) == puVar9[3]) {
                  lVar3 = lVar3 + (ulong)(*piVar11 + (uint)*(ushort *)((long)puVar9 + 0x4c)) * 0x10
                          + 0x130;
                  goto code_r0x029a36f0;
                }
                uVar6 = uVar6 + 1;
                piVar11 = piVar11 + 4;
              } while (uVar6 < *(ushort *)(lVar3 + 0x122));
            }
            lVar3 = func_0x010bc584(plVar5);
code_r0x029a36f0:
            uVar4 = *(undefined8 *)(lVar3 + 8);
          }
          puVar9 = (undefined8 *)func_0x010da294(uVar4,puVar9);
          UNRECOVERED_JUMPTABLE = (code *)*puVar9;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029a35a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE)(plVar5,puVar9);
      return;
    }
    if (((*(short *)((long)puVar9 + 0x4c) != -1) &&
        ((plVar8 == (long *)0x0 || ((*(byte *)(*plVar8 + 0x10d) & 1) == 0)))) &&
       (*(long *)(lVar3 + 0x18) != 0)) {
      uVar6 = func_0x01101d58(puVar9);
      func_0x01101dd4(puVar9);
      uVar7 = func_0x010ba65c();
      if ((uVar6 & 1) == 0) {
        uVar1 = *(ushort *)((long)puVar9 + 0x4c);
        if ((uVar7 & 1) == 0) {
          lVar3 = *plVar8 + (ulong)uVar1 * 0x10;
          UNRECOVERED_JUMPTABLE = *(code **)(lVar3 + 0x130);
          puVar9 = *(undefined8 **)(lVar3 + 0x138);
        }
        else {
          lVar3 = func_0x01101dd4(puVar9);
          lVar10 = *plVar8;
          if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
            uVar6 = 0;
            piVar11 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
            do {
              if (*(long *)(piVar11 + -2) == lVar3) {
                puVar9 = (undefined8 *)(lVar10 + (ulong)(*piVar11 + (uint)uVar1) * 0x10 + 0x130);
                goto code_r0x029a3720;
              }
              uVar6 = uVar6 + 1;
              piVar11 = piVar11 + 4;
            } while (uVar6 < *(ushort *)(lVar10 + 0x122));
          }
          puVar9 = (undefined8 *)func_0x010bc584(plVar8,lVar3,(ulong)uVar1);
code_r0x029a3720:
          UNRECOVERED_JUMPTABLE = (code *)*puVar9;
          puVar9 = (undefined8 *)puVar9[1];
        }
      }
      else {
        if ((uVar7 & 1) == 0) {
          uVar4 = *(undefined8 *)(*plVar8 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138);
        }
        else {
          lVar3 = *plVar8;
          if ((ulong)*(ushort *)(lVar3 + 0x122) != 0) {
            uVar6 = 0;
            piVar11 = (int *)(*(long *)(lVar3 + 0xb0) + 8);
            do {
              if (*(long *)(piVar11 + -2) == puVar9[3]) {
                lVar3 = lVar3 + (ulong)(*piVar11 + (uint)*(ushort *)((long)puVar9 + 0x4c)) * 0x10 +
                        0x130;
                goto code_r0x029a36bc;
              }
              uVar6 = uVar6 + 1;
              piVar11 = piVar11 + 4;
            } while (uVar6 < *(ushort *)(lVar3 + 0x122));
          }
          lVar3 = func_0x010bc584(plVar8);
code_r0x029a36bc:
          uVar4 = *(undefined8 *)(lVar3 + 8);
        }
        puVar9 = (undefined8 *)func_0x010da294(uVar4,puVar9);
        UNRECOVERED_JUMPTABLE = (code *)*puVar9;
      }
    }
  }
                    /* WARNING: Could not recover jumptable at 0x029a3584. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE)(plVar8,plVar5,puVar9);
  return;
}


