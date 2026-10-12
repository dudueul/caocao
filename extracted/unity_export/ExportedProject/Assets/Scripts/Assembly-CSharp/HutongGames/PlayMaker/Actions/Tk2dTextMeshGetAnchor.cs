namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshGetAnchor : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		[RequiredField]
		public FsmString textAnchorAsString;

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

		private void DoGetAnchor()
		{
		}
	}
}
