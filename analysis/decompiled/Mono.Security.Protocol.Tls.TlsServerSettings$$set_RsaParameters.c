// name: Mono.Security.Protocol.Tls.TlsServerSettings$$set_RsaParameters
// entry: 02b107ec

void Mono_Security_Protocol_Tls_TlsServerSettings__set_RsaParameters
               (long param_1,undefined8 *param_2)

{
  undefined8 uVar1;
  undefined8 uVar2;
  undefined8 uVar3;
  undefined8 uVar4;
  undefined8 uVar5;
  undefined8 uVar6;
  undefined8 uVar7;
  
  uVar3 = param_2[4];
  uVar2 = param_2[7];
  uVar1 = param_2[6];
  uVar7 = param_2[1];
  uVar6 = *param_2;
  uVar5 = param_2[3];
  uVar4 = param_2[2];
  *(undefined8 *)(param_1 + 0x48) = param_2[5];
  *(undefined8 *)(param_1 + 0x40) = uVar3;
  *(undefined8 *)(param_1 + 0x58) = uVar2;
  *(undefined8 *)(param_1 + 0x50) = uVar1;
  *(undefined8 *)(param_1 + 0x28) = uVar7;
  *(undefined8 *)(param_1 + 0x20) = uVar6;
  *(undefined8 *)(param_1 + 0x38) = uVar5;
  *(undefined8 *)(param_1 + 0x30) = uVar4;
  return;
}


