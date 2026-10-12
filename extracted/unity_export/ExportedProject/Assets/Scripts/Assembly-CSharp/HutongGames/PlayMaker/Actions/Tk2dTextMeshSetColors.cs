namespace HutongGames.PlayMaker.Actions
{
	public class Tk2dTextMeshSetColors : FsmStateAction
	{
		[RequiredField]
		public FsmOwnerDefault gameObject;

		public FsmColor mainColor;

		public FsmColor gradientColor;

		public FsmBool useGradient;

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

		private void DoSetColors()
		{
		}
	}
}
