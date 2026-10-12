// name: GuildWar.Backend.PImpl.SessionImpl$$OnSocketEnterToWorld
// entry: 01b32974

/* WARNING: Globals starting with '_' overlap smaller symbols at the same address */

void GuildWar_Backend_PImpl_SessionImpl__OnSocketEnterToWorld(long param_1,long param_2)

{
  ushort uVar1;
  uint uVar2;
  int iVar3;
  int iVar4;
  code *UNRECOVERED_JUMPTABLE_00;
  ulong uVar5;
  ulong uVar6;
  long *plVar7;
  undefined8 *puVar8;
  undefined8 uVar9;
  long lVar10;
  long lVar11;
  int *piVar12;
  
  if ((bRam00000000049fbe0d & 1) == 0) {
    func_0x010c65d4(0xc029);
    bRam00000000049fbe0d = 1;
  }
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  if (((*(byte *)(_class_Util_TypeInfo + 0x12a) & 1) != 0) &&
     (*(int *)(_class_Util_TypeInfo + 0xd8) == 0)) {
    func_0x010da55c(_class_Util_TypeInfo);
  }
  iVar3 = Util__GetData(0,uVar9,_str_clanDbShard,0,0);
  if (param_2 == 0) {
    func_0x010eec64(0);
  }
  uVar9 = SocketIO_SocketIOEvent__get_data(param_2,0);
  iVar4 = Util__GetData(0,uVar9,_str_worldID,0,0);
  if ((*(int *)(param_1 + 0xbc) == iVar3) && (*(int *)(param_1 + 0xc0) == iVar4)) {
    *(undefined4 *)(param_1 + 0xa8) = 0;
    *(undefined4 *)(param_1 + 0xbc) = 0;
    *(undefined4 *)(param_1 + 0xc0) = 0;
    lVar10 = *(long *)(param_1 + 0x40);
    *(int *)(param_1 + 0xcc) = iVar3;
    *(int *)(param_1 + 0xd0) = iVar4;
    if (lVar10 != 0) {
      if (*(long *)(lVar10 + 0x58) != 0) {
        System_Action__Invoke(*(long *)(lVar10 + 0x58),0);
      }
      UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar10 + 0x10);
      plVar7 = *(long **)(lVar10 + 0x20);
      puVar8 = *(undefined8 **)(lVar10 + 0x28);
      func_0x010da364(puVar8);
      uVar2 = func_0x01101d6c(puVar8);
      if ((uVar2 & 1) == 0) {
        if (*(char *)((long)puVar8 + 0x4e) != '\0') {
                    /* WARNING: Could not recover jumptable at 0x0109237c. Too many branches */
                    /* WARNING: Treating indirect jump as call */
          (*UNRECOVERED_JUMPTABLE_00)(0,plVar7,puVar8);
          return;
        }
        plVar7 = (long *)0x0;
      }
      else if ((*(short *)((long)puVar8 + 0x4c) != -1) &&
              (((plVar7 == (long *)0x0 || ((*(byte *)(*plVar7 + 0x10d) & 1) == 0)) &&
               (*(long *)(lVar10 + 0x18) != 0)))) {
        uVar5 = func_0x01101d58(puVar8);
        func_0x01101dd4(puVar8);
        uVar6 = func_0x010ba65c();
        if ((uVar5 & 1) == 0) {
          uVar1 = *(ushort *)((long)puVar8 + 0x4c);
          if ((uVar6 & 1) == 0) {
            lVar10 = *plVar7 + (ulong)uVar1 * 0x10;
            UNRECOVERED_JUMPTABLE_00 = *(code **)(lVar10 + 0x130);
            puVar8 = *(undefined8 **)(lVar10 + 0x138);
          }
          else {
            lVar10 = func_0x01101dd4(puVar8);
            lVar11 = *plVar7;
            if ((ulong)*(ushort *)(lVar11 + 0x122) != 0) {
              uVar5 = 0;
              piVar12 = (int *)(*(long *)(lVar11 + 0xb0) + 8);
              do {
                if (*(long *)(piVar12 + -2) == lVar10) {
                  puVar8 = (undefined8 *)(lVar11 + (ulong)(*piVar12 + (uint)uVar1) * 0x10 + 0x130);
                  goto code_r0x0109246c;
                }
                uVar5 = uVar5 + 1;
                piVar12 = piVar12 + 4;
              } while (uVar5 < *(ushort *)(lVar11 + 0x122));
            }
            puVar8 = (undefined8 *)func_0x010bc584(plVar7,lVar10,(ulong)uVar1);
code_r0x0109246c:
            UNRECOVERED_JUMPTABLE_00 = (code *)*puVar8;
            puVar8 = (undefined8 *)puVar8[1];
          }
        }
        else {
          if ((uVar6 & 1) == 0) {
            uVar9 = *(undefined8 *)
                     (*plVar7 + (ulong)*(ushort *)((long)puVar8 + 0x4c) * 0x10 + 0x138);
          }
          else {
            lVar10 = *plVar7;
            if ((ulong)*(ushort *)(lVar10 + 0x122) != 0) {
              uVar5 = 0;
              piVar12 = (int *)(*(long *)(lVar10 + 0xb0) + 8);
              do {
                if (*(long *)(piVar12 + -2) == puVar8[3]) {
                  lVar10 = lVar10 + (ulong)(*piVar12 + (uint)*(ushort *)((long)puVar8 + 0x4c)) *
                                    0x10 + 0x130;
                  goto code_r0x0109243c;
                }
                uVar5 = uVar5 + 1;
                piVar12 = piVar12 + 4;
              } while (uVar5 < *(ushort *)(lVar10 + 0x122));
            }
            lVar10 = func_0x010bc584(plVar7);
code_r0x0109243c:
            uVar9 = *(undefined8 *)(lVar10 + 8);
          }
          puVar8 = (undefined8 *)func_0x010da294(uVar9,puVar8);
          UNRECOVERED_JUMPTABLE_00 = (code *)*puVar8;
        }
      }
                    /* WARNING: Could not recover jumptable at 0x010923a4. Too many branches */
                    /* WARNING: Treating indirect jump as call */
      (*UNRECOVERED_JUMPTABLE_00)(plVar7,puVar8);
      return;
    }
  }
  return;
}


