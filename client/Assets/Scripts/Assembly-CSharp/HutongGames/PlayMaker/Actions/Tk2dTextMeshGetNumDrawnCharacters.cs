namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshGetNumDrawnCharacters : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmInt numDrawnCharacters;

		public bool everyframe;

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

		public override void OnUpdate()
		{
		}

		private void DoGetNumDrawnCharacters()
		{
		}
	}
}
