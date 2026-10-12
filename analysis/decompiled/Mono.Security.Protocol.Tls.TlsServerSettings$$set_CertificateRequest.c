// name: Mono.Security.Protocol.Tls.TlsServerSettings$$set_CertificateRequest
// entry: 02b10810

void Mono_Security_Protocol_Tls_TlsServerSettings__set_CertificateRequest(long param_1,byte param_2)

{
  *(byte *)(param_1 + 0x71) = param_2 & 1;
  return;
}


