
public class TClasificador {
	public static final int METODO_CLASIFICACION_INSERCION = 1;
	public static final int METODO_CLASIFICACION_SHELL = 2;
	public static final int METODO_CLASIFICACION_BURBUJA = 3;
	public static final int METODO_CLASIFICACION_RAPIDA = 4;
	//private static int LLAMADAS_AL_QUICK = 0;

	/**
	 * Punto de entrada al clasificador
	 * 
	 * @param metodoClasificacion
	 * @param orden
	 * @param tamanioVector
	 * @return Un vector del tam. solicitado, ordenado por el algoritmo solicitado
	 */
	public int[] clasificar(int[] datosParaClasificar, int metodoClasificacion) {
		switch (metodoClasificacion) {
		case METODO_CLASIFICACION_INSERCION:
			return ordenarPorInsercion(datosParaClasificar);
		case METODO_CLASIFICACION_SHELL:
			return ordenarPorShell(datosParaClasificar);
		case METODO_CLASIFICACION_BURBUJA:
			return ordenarPorBurbuja(datosParaClasificar);
		case METODO_CLASIFICACION_RAPIDA:
			//quickSort(datosParaClasificar, 0, datosParaClasificar.length-1);
			return datosParaClasificar;
		default:
			System.err.println("Este codigo no deberia haberse ejecutado");
			break;
		}
		return datosParaClasificar;
	}

	private void intercambiar(int[] vector, int pos1, int pos2) {
		int temp = vector[pos2];
		vector[pos2] = vector[pos1];
		vector[pos1] = temp;
	}


	/**
	 * @param datosParaClasificar
	 * @return
	 */
	private int[] ordenarPorShell(int[] datosParaClasificar) {
		int j, inc;
		int[] incrementos = new int[] { 3223, 358, 51, 10, 3, 1 };

		for (int posIncrementoActual = 0; posIncrementoActual < incrementos.length; posIncrementoActual++) {
			inc = incrementos[posIncrementoActual];
			if (inc < (datosParaClasificar.length / 2)) {
				for (int i = inc; i < datosParaClasificar.length; i++) {
					j = i - inc; // 0
					while (j >= 0 ) {
						if (datosParaClasificar[j] > datosParaClasificar[j + inc]) {
							intercambiar(datosParaClasificar, j, j + inc);
						} 
						j = j - inc;
					}
				}
			}
		}
		return datosParaClasificar;
	}


	/**
	 * @param datosParaClasificar
	 * @return
	 */
	protected int[] ordenarPorInsercion(int[] datosParaClasificar) {
		if (datosParaClasificar != null) {
			for (int i = 2; i <= datosParaClasificar.length; i++) {
				int j = i - 1;
				while ((j >= 1) && (datosParaClasificar[j-1] > datosParaClasificar[j])) {
					intercambiar(datosParaClasificar, j, j - 1);
					j--;
				}
			}
			return datosParaClasificar;
		}
		return null;
	}

	private boolean estaOrdenado(int[] arrayEntero){
		int i = 0;
		while (i<arrayEntero.length-1){
			if (arrayEntero[i] < arrayEntero[i+1]){
				i++;
			}
			else{
				return false;
			}
		}
		return true;
	}

	private int[] ordenarPorBurbuja(int[] datosParaClasificar) {
		//datosParaClasificar = null;
		int n = datosParaClasificar.length - 1;
		for (int i = 0; i < n; i++) {
			for (int j = n; j >= (i + 1); j--) {
				if (datosParaClasificar[j] < datosParaClasificar[j - 1]) {
					intercambiar(datosParaClasificar, j - 1, j);
				}
			}
		}
		return datosParaClasificar;
	}

	public void quickSort(int arr[], int left, int right, int[] profMax, int profActual) {
		if (profActual > profMax[0] ) {
			profMax[0] = profActual;
		}
        int index = partition(arr, left, right);
        if (left < index - 1) 
              quickSort(arr, left, index - 1, profMax, profActual+1);
        if (index < right)
              quickSort(arr, index, right, profMax, profActual+1);
    }

	protected int[] ordenarPorHeapSort(int[] datosParaClasificar) {
		for (int i = (datosParaClasificar.length - 1) / 2; i >= 0; i--) { //Armo el heap inicial de n-1 div 2 hasta 0
			armaHeap(datosParaClasificar, i, datosParaClasificar.length - 1);
		}
		for (int i = datosParaClasificar.length - 1; i  >= 1; i--) {
			intercambiar(datosParaClasificar,0,i);
			armaHeap(datosParaClasificar, 0, i-1);
		}
		return datosParaClasificar;
	}

	private void armaHeap(int[] datosParaClasificar, int primero, int ultimo) {
		if (primero < ultimo){
			int r = primero;
			while(r <= ultimo / 2){
				if (ultimo == 2*r){ //r tiene un hijo solo
					if (datosParaClasificar[r] > datosParaClasificar[r*2]){
						intercambiar(datosParaClasificar, r, 2 * r);
						r = 2 ;
					} else {
						r = ultimo;
					}
				} else { //r tiene 2 hijos
					int posicionIntercambio = 0;
					if (datosParaClasificar[2*r] > datosParaClasificar[2*r + 1]){
						posicionIntercambio = 2 * r +1;
					} else {
						posicionIntercambio = 2 * r;
					}
					if (datosParaClasificar[r] > datosParaClasificar[posicionIntercambio]){
						intercambiar(datosParaClasificar,r,posicionIntercambio);
						r = posicionIntercambio;
					} else {
						r = ultimo;
					}
				}
			}			
		}
	}

    private int partition(int arr[], int left, int right)
    {
          int i = left, j = right;
		  int izquierda = i;
		  int derecha = j;
          int tmp;

          int pivot = encuentraPivote(arr, left, right);

          while (i <= j) {
                while (arr[i] < pivot && (izquierda < j)) i++;
                while (arr[j] > pivot && (derecha > i)) j--;
                if (i <= j) {
                      tmp = arr[i];
                      arr[i] = arr[j];
                      arr[j] = tmp;
                      i++;
                      j--;
                }
          }
          return i;
    }

/*
    private int encuentraPivote(int[] vector, int l, int r){
        if (l == r ){
            return vector[l];
        }
        if (vector[l] < vector[l+1]) return vector[l];
        return vector[l+1];
    }
*/


	private int encuentraPivote(int[] vector, int l, int r){

		int casiMedio = 0;
		//if its even
		if ((r-l) % 2 == 0){
			casiMedio = (r+l)/2;
		}
		// odd
		else{
			casiMedio = (r+l)/2;
		}

		int dupla1 = Integer.max(vector[l], casiMedio);
		int dupla2 = Integer.max(vector[r], casiMedio);
		if (dupla1 != dupla2){
			//System.out.println(dupla1);
			//System.out.println(dupla2);
			return Integer.min(dupla1, dupla2);
		}
		else{
			//System.out.println(Integer.max(vector[l], vector[r]));
			return Integer.max(vector[l], vector[r]);
		}
	}
 


	public static void main(String args[]) {
		//int arr[] = { 12, 3, 5, 7, 4, 19, 26 };
		//int n = arr.length;
		//manerasPivote.findMedian(arr, n);

		// int[] profundidadMax = new int[]{0};
		// int profundidadesSumadas = 0;
		TClasificador clasif = new TClasificador();
		GeneradorDatosGenericos gdg = new GeneradorDatosGenericos();
		int[] vectorAleatorio = gdg.generarDatosAleatorios();
		// int[] vectorAscendente = gdg.generarDatosAscendentes();
		// int[] vectorDescendente = gdg.generarDatosDescendentes();
		// clasif.ordenarPorHeapSort(vectorAleatorio);
		// for (int i : vectorAleatorio) {
		// 	System.out.print(i + " ");
		// }
		// System.out.println(clasif.estaOrdenado(vectorAleatorio));
		// for (int i = 0; i < 50; i++){
		// 	clasif.ordenarPorHeapSort(vectorAleatorio)
		// 	vectorAleatorio = gdg.generarDatosDescendentes();
		// 	profundidadesSumadas += profundidadMax[0];
		// }
		long antes = System.nanoTime();
		clasif.ordenarPorHeapSort(vectorAleatorio);
		long despues = System.nanoTime();
		System.out.println(despues-antes);
		//for (int i = 0; i < vectorAleatorio.length; i++) {
		//	System.out.print(vectorAleatorio[i] + " ");
		//}
		// System.out.println("   ");
		// System.out.println("   ");
		// System.out.println("máximo de profundidad recursiva: " + profundidadesSumadas/50);
	/*	System.out.println("   ");
		System.out.println("   ");
		System.out.println(LLAMADAS_AL_QUICK);
		int a = LLAMADAS_AL_QUICK;
		System.out.println("   ");
		System.out.println("   ");
		*/
		//int[] resAscendente = clasif.clasificar(vectorAscendente, 4);
		//System.out.println(despues2-antes2);
		//for (int i = 0; i < resAscendente.length; i++) {
			//System.out.print(resAscendente[i] + " ");
		//}
		/*System.out.println("   ");
		System.out.println(LLAMADAS_AL_QUICK-a);
		System.out.println("   ");
		*/
		//int[] resDescendente = clasif.clasificar(vectorDescendente, 4);
		//System.out.println(despues3-antes3);
		//for (int i = 0; i < resDescendente.length; i++) {
			//System.out.print(resDescendente[i] + " ");
		//}
		/*
		System.out.println(clasif.estaOrdenado(resAleatorio));
		System.out.println(clasif.estaOrdenado(resAleatorio));
		System.out.println(clasif.estaOrdenado(resAleatorio));
		*/
		
	}

	public static int[] cambiarAvido(int importe, int[] monedas) {
        int cantMonedas = monedas.length;
        int[] salida = new int[cantMonedas];
        for (int i = cantMonedas - 1 ; i >= 0; i--) {
            while (monedas[i] <= importe) {
                salida[i]++;
                importe = importe - monedas[i]; 
            }
        }
        return salida;
    }
}
