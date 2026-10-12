namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshSetInlineStyling : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool inlineStyling;

		public FsmBool commit;

		private tk2dTextMesh _textMesh;

		private void _getTextMesh()
		{
		}

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		private void DoSetInlineStyling()
		{
		}
	}
}
