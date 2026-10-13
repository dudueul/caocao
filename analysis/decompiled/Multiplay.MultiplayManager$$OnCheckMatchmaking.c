// name: Multiplay.MultiplayManager$$OnCheckMatchmaking
// entry: 013a8044

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void Multiplay_MultiplayManager__OnCheckMatchmaking(long param_1,long param_2)

{
  ushort uVar1;
  int iVar2;
  uint uVar3;
  uint uVar4;
  long *plVar5;
  undefined8 uVar6;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar7;
  ulong uVar8;
  undefined8 *puVar9;
  long lVar10;
  long lVar11;
  int *piVar12;
  
  if ((bRam00000000049f7b08 & 1) == 0) {
    func_0x010c65d4(0x8a48);
    bRam00000000049f7b08 = 1;
  }
  plVar5 = *(long **)(param_1 + 0x40);
  if ((plVar5 == (long *)0x0) ||
     (iVar2 = (**(code **)(*plVar5 + 0x170))(plVar5,*(undefined8 *)(*plVar5 + 0x178)), iVar2 < 1)) {
    if (param_2 == 0) {
      func_0x010eec64(0);
    }
    uVar6 = SocketIO_SocketIOEvent__get_data(param_2,0);
    if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
       (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
      func_0x010da55c(_class_Util_TypeInfo);
    }
    uVar3 = Util__GetData(0,uVar6,_str_isRunningMatchmaking,1,0);
    lVar10 = *(long *)(param_1 + 0x98);
    if (lVar10 != 0) {
      uVar3 = uVar3 & 1;
      if (*(long *)(lVar10 + 0x58) != 0) {
        System_Action<bool>__Invoke
                  (*(long *)(lVar10 + 0x58),uVar3,_mm_Method_System_Action<bool>_Invoke__);
      }
      UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar10 + 0x10);
      plVar5 = *(long **)(lVar10 + 0x20);
      puVar9 = *(undefined8 **)(lVar10 + 0x28);
      func_0x010da364(puVar9);
      uVar4 = func_0x01101d6c(puVar9);
      if ((uVar4 & 1) == 0) {
        if (*(char *)((long)puVar9 + 0x4e) != '\x01') {
                    /* WARNING: Could not recover jumptable at 0x029a03c4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE_00)(0,plVar5,uVar3,puVar9);
          return;
        }
        plVar5 = (long *)0x0;
      }
      else if ((*(short *)((long)puVar9 + 0x4c) != -1) &&
              (((plVar5 == (long *)0x0 || ((*(byte *)(*plVar5 + 0x10d) & 1) == 0)) &&
               (*(long *)(lVar10 + 0x18) != 0)))) {
        uVar7 = func_0x01101d58(puVar9);
        func_0x01101dd4(puVar9);
        uVar8 = func_0x010ba65c();
        if ((uVar7 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar9 + 0x4c);
          if ((uVar8 & 1) == 0) {
            lVar10 = *plVar5 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar10 + 0x130);
            puVar9 = *(undefined8 **)(lVar10 + 0x138);
          }
          else {
            lVar10 = func_0x01101dd4(puVar9);
            lVar11 = *plVar5;
            if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
              uVar7 = 0;
              piVar12 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
              do {
                if (*(long *)(piVar12 + -2) == lVar10) {
                  puVar9 = (undefined8 *)(lVar11 + (ulong)(*piVar12 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x029a0490;
                }
                uVar7 = uVar7 + 1;
                piVar12 = piVar12 + 4;
              } while (uVar7 < *(ushort *)(lVar11 + 0x122));
            }
            puVar9 = (undefined8 *)func_0x010bc584(plVar5,lVar10,(ulong)uVar1);
code_r0x029a0490:
            UNRECOVERED_JUMPTABLE_00 = (code *)*puVar9;
            puVar9 = (undefined8 *)puVar9[1];
          }
        }
        else {
          if ((uVar8 & 1) == 0) {
            uVar6 = *(undefined8 *)
                     (*plVar5 + (ulong)*(ushort *)((long)puVar9 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar10 = *plVar5;
            if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
              uVar7 = 0;
              piVar12 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
              do {
                if (*(long *)(piVar12 + -2) == puVar9[3]) {
                  lVar10 = lVar10 + (ulong)(*piVar12 + (uint)*(ushort *)((long)puVar9 + 0x4c)) *
                                    0x10 + 0x130;
                  goto code_r0x029a045c;
                }
                uVar7 = uVar7 + 1;
                piVar12 = piVar12 + 4;
              } while (uVar7 < *(ushort *)(lVar10 + 0x122));
            }
            lVar10 = func_0x010bc584(plVar5);
code_r0x029a045c:
            uVar6 = *(undefined8 *)(lVar10 + 8);
          }
          puVar9 = (undefined8 *)func_0x010da294(uVar6,puVar9);
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar9;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x029a039c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(plVar5,uVar3,puVar9);
      return;
    }
  }
  return;
}


