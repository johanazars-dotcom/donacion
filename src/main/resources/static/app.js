const URL_BASE = 'http://localhost:8080/api';

/**
 * Petición centralizada con soporte para JWT y FormData (archivos)
 */
const peticion = async (url, metodo = 'GET', cuerpo = null) => {
    const config = { 
        method: metodo, 
        headers: { 'Content-Type': 'application/json' } 
    };
    if (cuerpo) config.body = JSON.stringify(cuerpo);
    
    try {
        const respuesta = await fetch(url, config);
        return respuesta;
    } catch (error) {
        console.error("Error de conexion:", error);
        return { ok: false };
    }
};

document.addEventListener('DOMContentLoaded', () => {
    const formLogin = document.getElementById('form-login');
    
    if (formLogin) {
        console.log("Formulario de login detectado");
        
        formLogin.addEventListener('submit', async (e) => {
            e.preventDefault();
            console.log("Boton presionado, enviando datos...");
            
            const email = document.getElementById('email').value;
            const password = document.getElementById('password').value;

            const res = await peticion(`${URL_BASE}/auth/login`, 'POST', { email, password });
            
            if (res.ok) {
                const data = await res.json();
                localStorage.setItem('jwt', data.token);
                console.log("Login exitoso, redirigiendo...");
                window.location.href = 'index.html';
            } else {
                console.log("Error en el login, estado:", res.status);
                alert('Credenciales incorrectas o error de servidor');
            }
        });
    }
});
// --- MÓDULO DE DONANTES ---
const manejarDonantes = () => {
    const form = document.getElementById('form-donante');
    if (!form) return;

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const id = document.getElementById('donanteId').value;
        
        const donante = {
            nombres: document.getElementById('nombres').value,
            apellidos: document.getElementById('apellidos').value,
            documento: document.getElementById('documento').value,
            fechaNacimiento: document.getElementById('fechaNacimiento').value,
            tipoSangre: document.getElementById('tipoSangre').value,
            peso: parseFloat(document.getElementById('peso').value),
            telefono: document.getElementById('telefono').value,
            correo: document.getElementById('correo').value,
            direccion: document.getElementById('direccion').value,
            aceptaConsentimiento: document.getElementById('aceptaConsentimiento').checked
        };

        const formData = new FormData();
        formData.append('datos', JSON.stringify(donante));

        const archivoFirma = document.getElementById('firmaDocumento').files[0];
        if (archivoFirma) {
            formData.append('firma', archivoFirma);
        } else if (!id) {
            alert('Por favor selecciona una imagen para la firma');
            return;
        }

        const url = id ? `${URL_BASE}/donantes/${id}` : `${URL_BASE}/donantes`;
        const metodo = id ? 'PUT' : 'POST';

        const res = await peticion(url, metodo, formData);
        
        if (res.ok) {
            alert('Operación realizada con éxito');
            location.reload();
        } else {
            alert('Error al procesar la solicitud');
        }
    });
};

const obtenerDonantes = async () => {
    const tabla = document.querySelector('#tabla-donantes tbody');
    if (!tabla) return;

    const res = await peticion(`${URL_BASE}/donantes`);
    if (!res.ok) return;
    
    const lista = await res.json();
    tabla.innerHTML = lista.length === 0 ? '<tr><td colspan="5">No hay donantes registrados.</td></tr>' :
        lista.map(d => `
        <tr>
            <td>${d.id}</td>
            <td>${d.documento}</td>
            <td>${d.nombres} ${d.apellidos}</td>
            <td>${d.tipoSangre}</td>
            <td>
                <button class="btn-edit" onclick="window.cargarDonante(${d.id})">Editar</button>
                <button class="btn-delete" onclick="window.eliminarDonante(${d.id})">Borrar</button>
            </td>
        </tr>`).join('');
};

const cargarDonante = async (id) => {
    const res = await peticion(`${URL_BASE}/donantes/${id}`);
    if (res.ok) {
        const d = await res.json();
        document.getElementById('donanteId').value = d.id;
        document.getElementById('nombres').value = d.nombres;
        document.getElementById('apellidos').value = d.apellidos;
        document.getElementById('documento').value = d.documento;
        document.getElementById('fechaNacimiento').value = d.fechaNacimiento;
        document.getElementById('tipoSangre').value = d.tipoSangre;
        document.getElementById('peso').value = d.peso;
        document.getElementById('telefono').value = d.telefono;
        document.getElementById('correo').value = d.correo;
        document.getElementById('direccion').value = d.direccion;
        document.getElementById('aceptaConsentimiento').checked = d.aceptaConsentimiento;
        document.getElementById('btn-submit').textContent = 'Actualizar Donante';
        window.scrollTo({ top: 0, behavior: 'smooth' });
    }
};

const eliminarDonante = async (id) => {
    if (confirm('¿Seguro que deseas eliminar a este donante?')) {
        const res = await peticion(`${URL_BASE}/donantes/${id}`, 'DELETE');
        if (res.ok) location.reload();
    }
};

// --- OTROS MÓDULOS ---
const exportarPdf = () => window.location.href = `${URL_BASE}/donantes/exportar-pdf`;

const registrarDonacion = async () => {
    const form = document.getElementById('form-donacion');
    if (!form) return;
    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const donacion = {
            donanteId: parseInt(document.getElementById('idDonanteDonacion').value),
            cantidadMl: parseFloat(document.getElementById('cantidadMl').value),
            codigoDonacion: 'DON-' + Math.floor(Math.random() * 9000 + 1000),
            observaciones: document.getElementById('observaciones').value,
            fechaDonacion: new Date().toISOString()
        };
        const res = await peticion(`${URL_BASE}/donaciones`, 'POST', donacion);
        if (res.ok) { alert('Donación registrada'); location.reload(); }
    });
};

// --- INICIALIZADOR ---
document.addEventListener('DOMContentLoaded', () => {
    // Si no estamos en login, verificar que haya token
    if (!window.location.pathname.includes('login.html') && !localStorage.getItem('jwt')) {
        window.location.href = 'login.html';
    }
    
    manejarLogin();
    manejarDonantes();
    obtenerDonantes();
    registrarDonacion();
});

window.cargarDonante = cargarDonante;
window.eliminarDonante = eliminarDonante;
window.exportarPdf = exportarPdf;