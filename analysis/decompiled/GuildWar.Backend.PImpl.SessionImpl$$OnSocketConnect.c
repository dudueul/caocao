// name: GuildWar.Backend.PImpl.SessionImpl$$OnSocketConnect
// entry: 01b328a8

void GuildWar_Backend_PImpl_SessionImpl__OnSocketConnect(long param_1)

{
  ushort uVar1;
  uint uVar2;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar3;
  ulong uVar4;
  long *plVar5;
  undefined8 uVar6;
  undefined8 *puVar7;
  long lVar8;
  long lVar9;
  int *piVar10;
  
  *(undefined4 *)(param_1 + 0x98) = 0;
  *(undefined1 *)(param_1 + 0x9c) = 0;
  *(undefined1 *)(param_1 + 0xa6) = 0;
  *(undefined1 *)(param_1 + 0x9d) = 1;
  *(undefined4 *)(param_1 + 0xa0) = 0x40a00000;
  *(undefined2 *)(param_1 + 0xa4) = 0;
  GuildWar_Backend_PImpl_SessionImpl__ResetSocketWorldRooms();
  lVar8 = *(long *)(param_1 + 0x28);
  if (lVar8 == 0) {
    return;
  }
  if (*(long *)(lVar8 + 0x58) != 0) {
    System_Action__Invoke(*(long *)(lVar8 + 0x58),0);
  }
  UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar8 + 0x10);
  plVar5 = *(long **)(lVar8 + 0x20);
  puVar7 = *(undefined8 **)(lVar8 + 0x28);
  func_0x010da364(puVar7);
  uVar2 = func_0x01101d6c(puVar7);
  if ((uVar2 & 1) == 0) {
    if (*(char *)((long)puVar7 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(0,plVar5,puVar7);
      return;
    }
    plVar5 = (long *)0x0;
  }
  else if ((*(short *)((long)puVar7 + 0x4c) != -1) &&
          (((plVar5 == (long *)0x0 || ((*(byte *)(*plVar5 + 0x10d) & 1) == 0)) &&
           (*(long *)(lVar8 + 0x18) != 0)))) {
    uVar3 = func_0x01101d58(puVar7);
    func_0x01101dd4(puVar7);
    uVar4 = func_0x010ba65c();
    if ((uVar3 & 1) == 0) {
      uVar1 = *(ushort *)((long)puVar7 + 0x4c);
      if ((uVar4 & 1) == 0) {
        lVar8 = *plVar5 + (ulong)uVar1 * 0x10;
        UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar8 + 0x130);
        puVar7 = *(undefined8 **)(lVar8 + 0x138);
      }
      else {
        lVar8 = func_0x01101dd4(puVar7);
        lVar9 = *plVar5;
        if ((ulong)*(ushort *)(lVar9 + 0x122) != 0) {
          uVar3 = 0;
          piVar10 = (int *)(*(long *)(lVar9 + 0xb0) + 8);
          do {
            if (*(long *)(piVar10 + -2) == lVar8) {
              puVar7 = (undefined8 *)(lVar9 + (ulong)(*piVar10 + (uint)uVar1) * 0x10 + 0x130);
              goto code_r0x0109246c;
            }
            uVar3 = uVar3 + 1;
            piVar10 = piVar10 + 4;
          } while (uVar3 < *(ushort *)(lVar9 + 0x122));
        }
        puVar7 = (undefined8 *)func_0x010bc584(plVar5,lVar8,(ulong)uVar1);
code_r0x0109246c:
        UNRECOVERED_JUMPTABLE_00 = (code *)*puVar7;
        puVar7 = (undefined8 *)puVar7[1];
      }
    }
    else {
      if ((uVar4 & 1) == 0) {
        uVar6 = *(undefined8 *)(*plVar5 + (ulong)*(ushort *)((long)puVar7 + 0x4c) * 0x10 + 0x138);
      }
      else {
        lVar8 = *plVar5;
        if ((ulong)*(ushort *)(lVar8 + 0x122) != 0) {
          uVar3 = 0;
          piVar10 = (int *)(*(long *)(lVar8 + 0xb0) + 8);
          do {
            if (*(long *)(piVar10 + -2) == puVar7[3]) {
              lVar8 = lVar8 + (ulong)(*piVar10 + (uint)*(ushort *)((long)puVar7 + 0x4c)) * 0x10 +
                      0x130;
              goto code_r0x0109243c;
            }
            uVar3 = uVar3 + 1;
            piVar10 = piVar10 + 4;
          } while (uVar3 < *(ushort *)(lVar8 + 0x122));
        }
        lVar8 = func_0x010bc584(plVar5);
code_r0x0109243c:
        uVar6 = *(undefined8 *)(lVar8 + 8);
      }
      puVar7 = (undefined8 *)func_0x010da294(uVar6,puVar7);
      UNRECOVERED_JUMPTABLE_00 = (code *)*puVar7;
    }
  }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
  (*UNRECOVERED_JUMPTABLE_00)(plVar5,puVar7);
  return;
}


