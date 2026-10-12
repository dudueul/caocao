namespace HutongGames.PlayMaker.Actions
{
	public class ConvertMaterialToObject : FsmStateAction
	{
		[RequiredField]
		public FsmMaterial materialVariable;

		[RequiredField]
		public FsmObject objectVariable;

		public bool everyFrame;

		public override void Reset()
		{
		}

		public override void OnEnter()
		{
		}

		public override void OnUpdate()
		{
		}

		private void DoConvertMaterialToObject()
		{
		}
	}
}
