namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshSetPixelPerfect : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmBool pixelPerfect;

		public FsmBool commit;

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

		private void DoSetPixelPerfect()
		{
		}
	}
}
