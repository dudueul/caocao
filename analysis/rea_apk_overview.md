## error

Analysis failed

## code

capability_unavailable

## category

unsupported_provider

## message

The selected analysis provider does not support this target. Choose another provider or open a supported extracted binary.

## retryable

false

## remediation

| Key    | Value                                                                                                                      |
|--------|----------------------------------------------------------------------------------------------------------------------------|
| action | The selected analysis provider does not support this target. Choose another provider or open a supported extracted binary. |

## details.operation

current_document

## details.selection_reason

target_unsupported

## details.requested_provider_id

auto

## details.candidate_ids

ghidra,hopper,ida

## details.rejections

| provider_id | code                    | reason                                                                                                  | diagnostics     |
|-------------|-------------------------|---------------------------------------------------------------------------------------------------------|-----------------|
| ghidra      | target_kind_unsupported | Ghidra v1 imports executable targets, not archive targets.                                              | [object Object] |
| hopper      | executable_missing      | Hopper launcher is missing or not executable: /opt/hopper/bin/Hopper                                    | [object Object] |
| hopper      | target_kind_unsupported | Hopper cannot directly analyze archive targets.                                                         | [object Object] |
| ida         | not_configured          | Set REA_IDA_MCP_CONFIG to an upstream IDA MCP JSON registration; see docs/ida-provider.md.              | [object Object] |
| ida         | target_kind_unsupported | IDA MCP integration admits executable inputs, not analysis databases. Select the original input binary. | [object Object] |
