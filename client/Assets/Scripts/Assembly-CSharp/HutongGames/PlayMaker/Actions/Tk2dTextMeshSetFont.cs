namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshSetFont : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmGameObject font;

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

		private void DoSetFont()
		{
		}
	}
}
