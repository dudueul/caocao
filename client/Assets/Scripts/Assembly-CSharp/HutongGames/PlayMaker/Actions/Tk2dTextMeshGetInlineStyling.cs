namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshGetInlineStyling : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmBool inlineStyling;

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

		private void DoGetInlineStyling()
		{
		}
	}
}
